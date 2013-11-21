package cdp.calculator.calculator;

import cdp.calculator.evaluator.IEvaluator;
import cdp.calculator.exception.CalculationException;
import cdp.calculator.exception.EvaluatorException;
import cdp.calculator.exception.ValidationException;
import cdp.calculator.validator.IValidator;

public class Calculator implements ICalculator {

    private final IEvaluator evaluator;

    private final IValidator validator;

    public Calculator(IValidator validator, IEvaluator evaluator) {
        this.evaluator = evaluator;
        this.validator = validator;
    }

    public double calculate(String expression, byte precision) throws CalculationException {
        double result;
        try {
            validator.validate(expression);
            result = evaluator.evaluate(expression);
        } catch (EvaluatorException e) {
            throw new CalculationException("Evaluation error. " + e.getMessage());
        } catch (ValidationException e) {
            throw new CalculationException("Validation error. " + e.getMessage());
        } catch (Throwable e) {
            throw new CalculationException("Unexpected error. " + e);
        }
        return result;
    }
}
