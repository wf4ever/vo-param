package exeptions;

/**
<<<<<<< HEAD
 * @author Carlo Maria Zwlf, Lerma/ObsPM
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM
>>>>>>> master
 * Class describing the exception to be trown when 
 * a parameter is invalid
 */
public class InvalidParameterException extends PDLException {
	private static final long serialVersionUID = -5315405384001086653L;

	public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cuase) {
        super(message, cuase);
    }
}
