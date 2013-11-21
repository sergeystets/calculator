package cdp.calculator.validator;

import org.apache.commons.lang.StringUtils;

import cdp.calculator.exception.ValidationException;

public class Validator implements IValidator {

	public void validate(String expression) throws ValidationException {
		if (StringUtils.isBlank(expression)) {
			throw new ValidationException("Expression should not be empty.");
		}
		if (StringUtils.isBlank(expression.trim())) {
			throw new ValidationException("Expression should not be empty.");
		}
	}
}
