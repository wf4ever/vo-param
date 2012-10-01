package visitors;

import java.security.InvalidParameterException;

/**
 * @author Carlo Maria Zwlf, Lerma/ObsPM 
 * 
 * Implementation of interface Icreteria for testing Double parameter type
 *
 */

public class RealCriteria implements Icriteria {

	private static final String authorizedCriteriaType = "real";

	public boolean VerifyCriteria(String type, String value) {
		boolean isTypeDouble = false;
		if (type.equalsIgnoreCase(this.getAuthorizedCriteriaType())) {
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

	public String getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

}
