package visitors;

import CommonsObjects.GeneralParameter;

/**
 * @author Carlo Maria Zwolf, Lerma/ObsPM
 * 
 *         General interface for implementing the visitor design pattern
 */
public interface Ivisitor {
	/**
	 * This function verify the data contained in the parameter passed as
	 * argument. The first implementation of this Interface is the abstract
	 * class AbstractVisitor. If the parameter is valid with respect to the
	 * defined criteria, than the function visit will end without any exception.
	 * In the opposite case exceptions are thrown.
	 * 
	 * @param A
	 */
	public void visit(GeneralParameter A);
}
