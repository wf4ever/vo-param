package visitors;

import net.ivoa.parameter.model.ParameterType;

/**
<<<<<<< HEAD
 * @author Carlo Maria Zwlf, Lerma/ObsPM 
=======
 * @author Carlo Maria Zw�lf, Lerma/ObsPM 
>>>>>>> master
 * 
 * Implementation of interface Icreteria for testing String parameter type
 *
 */

public class StringCriteria implements Icriteria{
	
	private static final ParameterType authorizedCriteriaType = ParameterType.STRING;
	
	public ParameterType getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}


	public boolean VerifyCriteria(ParameterType type, String value) {
		boolean isTypeString = false;
		if (type == this.getAuthorizedCriteriaType()) {
			// The type is known
			isTypeString = true;
		}
		return isTypeString;
	}

}
