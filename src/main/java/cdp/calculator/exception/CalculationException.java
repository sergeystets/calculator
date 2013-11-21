package cdp.calculator.exception;

public class CalculationException extends Exception {

    private static final long serialVersionUID = 9216689430917781803L;

    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculationException(String message) {
        super(message);
    }

}
