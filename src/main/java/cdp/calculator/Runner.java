package cdp.calculator;

import org.apache.log4j.Logger;

import cdp.calculator.calculator.Calculator;
import cdp.calculator.calculator.ICalculator;
import cdp.calculator.evaluator.RPNEvaluator;
import cdp.calculator.exception.CalculationException;
import cdp.calculator.operation.OperationProvider;
import cdp.calculator.validator.Validator;

public class Runner {

    private static final Logger logger = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        ICalculator calculator = new Calculator(new Validator(), new RPNEvaluator(new OperationProvider()));
        final String input = System.getenv("input");
        logger.trace("Input: " + input);
        byte precision = 2;
        try {
            Double result = calculator.calculate(input, precision);
            System.out.println(String.valueOf(result));
        } catch (CalculationException e) {
            System.out.println("Fail");

            logger.error(e.getMessage());
        }
    }
}
