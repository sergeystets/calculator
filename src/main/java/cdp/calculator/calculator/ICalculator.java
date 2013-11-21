package cdp.calculator.calculator;

import cdp.calculator.exception.CalculationException;

public interface ICalculator {
	
	double calculate(String expression, byte precision) throws CalculationException;
 
}
