package net.ivoa.pdl.interpreter.expression.exceptions;

public class InvalidCondition extends Exception {
	private static final long serialVersionUID = -5315405384001086653L;

	public InvalidCondition(String message) {
        super(message);
    }

    public InvalidCondition(String message, Throwable cuase) {
        super(message, cuase);
    }
}