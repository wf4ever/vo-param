package visitors;

import java.security.InvalidParameterException;

import net.ivoa.parameter.model.ParameterType;

/**
<<<<<<< HEAD
 * @author Carlo Maria Zwlf, Lerma/ObsPM 
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM 
>>>>>>> master
 * 
 * Implementation of interface Icreteria for testing Double parameter type
 *
 */

public class RealCriteria implements Icriteria {

	private static final ParameterType authorizedCriteriaType = ParameterType.REAL;

	public boolean VerifyCriteria(ParameterType type, String value) {
		boolean isTypeDouble = false;
		if (type == this.getAuthorizedCriteriaType()) {
			// The type is known
			isTypeDouble = true;
			// Verify that value could be cast to double
			try {
				Double.parseDouble(value);
			} catch (Exception e) {
				throw new InvalidParameterException("can't cast " + value
						+ " to double");
			}
		}
		return isTypeDouble;
	}

	public ParameterType getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

}
