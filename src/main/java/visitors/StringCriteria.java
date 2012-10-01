package visitors;

/**
 * @author Carlo Maria Zwlf, Lerma/ObsPM 
 * 
 * Implementation of interface Icreteria for testing String parameter type
 *
 */

public class StringCriteria implements Icriteria{
	
	private static final String authorizedCriteriaType = "String";
	
	public String getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}


	public boolean VerifyCriteria(String type, String value) {
		boolean isTypeString = false;
		if (type.equalsIgnoreCase(this.getAuthorizedCriteriaType())) {
			// The type is known
			isTypeString = true;
		}
		return isTypeString;
	}

}
