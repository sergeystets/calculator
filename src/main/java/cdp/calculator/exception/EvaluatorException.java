package cdp.calculator.exception;

public class EvaluatorException extends Exception {

    private static final long serialVersionUID = 5538175220312922646L;

    public EvaluatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluatorException(String message) {
        super(message);
    }
}
