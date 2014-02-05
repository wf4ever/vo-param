package visitors;

import java.security.InvalidParameterException;

import net.ivoa.parameter.model.ParameterType;

/**
<<<<<<< HEAD
 * @author Carlo Maria Zwölf, Lerma/ObsPM 
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM 
>>>>>>> master
 * 
 * Implementation of interface Icreteria for testing Boolean parameter type
 *
 */

public class BooleanCriteria implements Icriteria{
	
	private static final ParameterType authorizedCriteriaType = ParameterType.BOOLEAN;
	
	public ParameterType getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

	public boolean VerifyCriteria(ParameterType type, String value) {
		boolean isTypeBoolean = false;
		if (type == this.getAuthorizedCriteriaType()) {
			// The type is known
			isTypeBoolean = true;
			// Verify that value could be cast to boolean
			if (!(value.equalsIgnoreCase("true") || value
					.equalsIgnoreCase("false"))) {
				throw new InvalidParameterException("can't cast " + value + " to boolean");
			}
		}
		return isTypeBoolean;
	}

}
