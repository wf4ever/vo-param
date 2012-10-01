package visitors;

import java.security.InvalidParameterException;

/**
 * @author Carlo Maria Zwölf, Lerma/ObsPM 
 * 
 * Implementation of interface Icreteria for testing Boolean parameter type
 *
 */

public class BooleanCriteria implements Icriteria{
	
	private static final String authorizedCriteriaType = "Boolean";
	
	public String getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

	public boolean VerifyCriteria(String type, String value) {
		boolean isTypeBoolean = false;
		if (type.equalsIgnoreCase(this.getAuthorizedCriteriaType())) {
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
