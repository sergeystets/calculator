package cdp.calculator.validator;

import cdp.calculator.exception.ValidationException;

public interface IValidator {
	
	void validate(String expression) throws ValidationException;

}
