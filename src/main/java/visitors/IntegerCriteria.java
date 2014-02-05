package visitors;

import java.security.InvalidParameterException;

import org.omg.Dynamic.Parameter;

import net.ivoa.parameter.model.ParameterType;

/**
<<<<<<< HEAD
 * @author Carlo Maria Zwlf, Lerma/ObsPM
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM
>>>>>>> master
 * 
 *         Implementation of interface Icreteria for testing Integer parameter
 *         type
 * 
 */
public class IntegerCriteria implements Icriteria {

	private static final ParameterType authorizedCriteriaType = ParameterType.INTEGER;

	public ParameterType getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

	public boolean VerifyCriteria(ParameterType type, String value) {
		boolean isTypeInteger = false;
		if (type== this.getAuthorizedCriteriaType()) {
			// The type is known
			isTypeInteger = true;
			// Verify that value could be cast to integer
			try {
				Integer.parseInt(value);
			} catch (Exception e) {
				throw new InvalidParameterException("can't cast " + value
						+ " to Integer");
			}
		}
		return isTypeInteger;
	}

}
