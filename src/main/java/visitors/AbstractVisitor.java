package visitors;

import java.security.InvalidParameterException;
import java.util.List;

import net.ivoa.parameter.model.ParameterType;
import CommonsObjects.GeneralParameter;

/**
<<<<<<< HEAD
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM
>>>>>>> master
 * 
 *         Abstract class implementing the Ivisitor interface. In this class
 *         only the function buildCriteriaList is abstract. The function visit
 *         really implements this method.
 * 
 */
public abstract class AbstractVisitor implements Ivisitor {

	/**
	 * This function returns the list of criteria that will be checked inside
	 * the visit function. User has to implement this functions in order to
	 * define his list of criteria.
	 * 
	 * @return the list of criteria
	 */
	public abstract List<Icriteria> buildCriteriaList();

	public void visit(GeneralParameter A) {
		ParameterType type = A.getType();
		String value = A.getValue();

		// Build the list of criteria that must be verified
		List<Icriteria> criteriaList = this.buildCriteriaList();
		Boolean isTypeHandled = false;
		// Verify each criteria
		for (Icriteria localCriteria : criteriaList) {
			isTypeHandled = isTypeHandled
					|| localCriteria.VerifyCriteria(type, value);
		}
		if (!isTypeHandled) {
			// I the program reach this point, the type of parameter in not
			// handled in any criteria
			throw new InvalidParameterException("The type " + type
					+ " does not correspond to any supported type");
		}
	}
}
