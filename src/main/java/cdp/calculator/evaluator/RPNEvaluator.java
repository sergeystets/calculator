package cdp.calculator.evaluator;

import cdp.calculator.exception.EvaluatorException;
import cdp.calculator.operation.IOperation;
import cdp.calculator.operation.OperationProvider;
import cdp.calculator.util.ParseUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class RPNEvaluator implements IEvaluator {

    private static final Logger logger = Logger.getLogger(RPNEvaluator.class);

    private final OperationProvider operationProvider;

    public RPNEvaluator(OperationProvider operationProvider) {
        this.operationProvider = operationProvider;
    }

    @Override
    public double evaluate(String expression) throws EvaluatorException {
        Assert.hasText(expression, "expression can not be null or empty");

        Stack<String> rpnStack = buildRPNStack(expression);
        logger.trace("RPN Stack->" + rpnStack);
        Collections.reverse(rpnStack);

        Stack<Double> resultStack = new Stack<Double>();

        while (!rpnStack.isEmpty()) {
            String token = rpnStack.pop();
            if (operationProvider.isOperation(token)) {
                IOperation operation = operationProvider.getOperation(token);
                int numOfOperands = operation.getNumOfOperands();
                Double[] operands = popOperands(resultStack, numOfOperands);
                double operationResult = operation.execute(operands);
                resultStack.push(operationResult);
                continue;
            }
            resultStack.push(ParseUtils.getAsNumber(token));
        }

        return getFirst(resultStack);
    }

    private Stack<String> buildRPNStack(String expression) throws EvaluatorException {
        expression = prepareExpression(expression);
        Stack<String> rpnStack = new Stack<String>();
        Stack<IOperation> operationsStack = new Stack<IOperation>();

        StringTokenizer tokens = new StringTokenizer(expression, operationProvider.getOperationNames(), true);

        int count = 0;
        while (tokens.hasMoreTokens()) {
            count++;
            String token = tokens.nextToken();
            if (operationProvider.isOperation(token)) {
                handleOperation(operationsStack, rpnStack, operationProvider.getOperation(token));
            } else if (ParseUtils.isNumber(token)) {
                rpnStack.push(token);
            } else {
                throw new EvaluatorException("Unrecognized token:" + token + " at position: " + count);
            }
        }

        pushOperationsToRPNStack(operationsStack, rpnStack);

        return rpnStack;
    }

    private void handleOperation(Stack<IOperation> operationsStack, Stack<String> rpnStack, IOperation operation) {
        while (!operationsStack.isEmpty()) {
            IOperation stackOperation = operationsStack.lastElement();
            if (stackOperation.getPriority() >= operation.getPriority()) {
                rpnStack.push(operationsStack.pop().getName());
            } else {
                break;
            }
        }
        operationsStack.push(operation);
    }

    private void pushOperationsToRPNStack(Stack<IOperation> operationsStack, Stack<String> rpnStack) {
        while (!operationsStack.isEmpty()) {
            rpnStack.push(operationsStack.pop().getName());
        }
    }

    private Double[] popOperands(Stack<Double> resultStack, int numOfOperands) {
        Stack<Double> operands = new Stack<Double>();
        for (int i = 0; i < numOfOperands; i++) {
            operands.push(resultStack.pop());
        }
        Collections.reverse(operands);
        return operands.<Double>toArray(new Double[operands.size()]);
    }

    private double getFirst(Stack<Double> result) throws EvaluatorException {
        if (result == null || result.size() != 1) {
            throw new EvaluatorException("Unexpected result");
        }
        return result.get(0);
    }

    private String prepareExpression(String expression) {
        expression = expression.replace(" ", "");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        return expression;
    }
}