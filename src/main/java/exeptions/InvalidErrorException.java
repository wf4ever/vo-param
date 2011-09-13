package exeptions;

/**
 * @author Carlo Maria Zwšlf, Lerma/ObsPM
 * Class describing the exception to be trown when 
 * a Error object instance is invalid
 */
public class InvalidErrorException extends Exception {
	private static final long serialVersionUID = -5315405384001086651L;

	public InvalidErrorException(String message) {
        super(message);
    }

    public InvalidErrorException(String message, Throwable cuase) {
        super(message, cuase);
    }
}
