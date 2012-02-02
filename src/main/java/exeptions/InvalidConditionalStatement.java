package exeptions;

public class InvalidConditionalStatement extends Exception {
	private static final long serialVersionUID = -5315405384001086653L;

	public InvalidConditionalStatement(String message) {
        super(message);
    }

    public InvalidConditionalStatement(String message, Throwable cuase) {
        super(message, cuase);
    }
}