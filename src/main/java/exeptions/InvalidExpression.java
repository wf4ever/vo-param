package exeptions;

public class InvalidExpression extends Exception {
	private static final long serialVersionUID = -5315405384001086653L;

	public InvalidExpression(String message) {
        super(message);
    }

    public InvalidExpression(String message, Throwable cuase) {
        super(message, cuase);
    }
}