package cdp.calculator.evaluator;

import cdp.calculator.exception.EvaluatorException;

public interface IEvaluator {
	
	double evaluate(String expression) throws EvaluatorException;

}
