package exeptions;

public class InvalidCriterion extends PDLException {
	private static final long serialVersionUID = -5315405384001086653L;

	public InvalidCriterion(String message) {
        super(message);
    }

    public InvalidCriterion(String message, Throwable cuase) {
        super(message, cuase);
    }
}