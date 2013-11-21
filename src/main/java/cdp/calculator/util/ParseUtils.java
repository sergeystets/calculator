package cdp.calculator.util;

import org.springframework.util.Assert;

public class ParseUtils {

	public static boolean isNumber(String token) {
		Assert.hasText(token, "token can not be null or empty");
		try {
			Double.parseDouble(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static double getAsNumber(String expression) throws NumberFormatException{
		Assert.hasText(expression, "token can not be null or empty");
		return Double.parseDouble(expression);
	}


}
