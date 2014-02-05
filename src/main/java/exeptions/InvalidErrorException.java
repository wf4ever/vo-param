package exeptions;

/**
<<<<<<< HEAD
 * @author Carlo Maria Zwlf, Lerma/ObsPM
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM
>>>>>>> master
 * Class describing the exception to be trown when 
 * a Error object instance is invalid
 */
public class InvalidErrorException extends PDLException {
	private static final long serialVersionUID = -5315405384001086651L;

	public InvalidErrorException(String message) {
        super(message);
    }

    public InvalidErrorException(String message, Throwable cuase) {
        super(message, cuase);
    }
}
