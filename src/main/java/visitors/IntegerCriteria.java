package visitors;

import java.security.InvalidParameterException;

/**
 * @author Carlo Maria Zwšlf, Lerma/ObsPM
 * 
 *         Implementation of interface Icreteria for testing Integer parameter
 *         type
 * 
 */
public class IntegerCriteria implements Icriteria {

	private static final String authorizedCriteriaType = "Integer";

	public String getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

	public boolean VerifyCriteria(String type, String value) {
		boolean isTypeInteger = false;
		if (type.equalsIgnoreCase(this.getAuthorizedCriteriaType())) {
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
