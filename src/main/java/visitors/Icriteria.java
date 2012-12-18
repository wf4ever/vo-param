package visitors;

import net.ivoa.parameter.model.ParameterType;

/**
 * @author Carlo Maria Zw�lf, Lerma/ObsPM 
 * 
 * General Interface for Criteria. A given Criteria is composed of a set of properties
 * that a parameter must satisfy to be valid 
 */
public interface Icriteria {
	
	
	/**
	 * return a String containing the type that is authorized by the criteria
	 * @return
	 */
	public ParameterType getAuthorizedCriteriaType();
	
	/**
	 * Return true if the criteria is satisfied, false in the other case
	 * @param type : the type of the parameter
	 * @param value : the value of the parameter
	 * @return
	 */
	public boolean VerifyCriteria(ParameterType type, String value);
}
