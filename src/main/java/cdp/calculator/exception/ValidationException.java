package cdp.calculator.exception;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 7318016056112220443L;

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }

}
