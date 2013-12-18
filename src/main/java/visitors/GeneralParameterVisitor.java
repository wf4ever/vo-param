package visitors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlo Maria Zwolf, Lerma/ObsPM
 * 
 *         Simple implementation of the abstract visitor, for handling basic type
 *         based parameters (Real, Integer, Boolean and String)
 */
public class GeneralParameterVisitor extends AbstractVisitor {

	@Override
	public List<Icriteria> buildCriteriaList() {
		List<Icriteria> criteriaList = new ArrayList<Icriteria>();
		criteriaList.add(new RealCriteria());
		criteriaList.add(new IntegerCriteria());
		criteriaList.add(new BooleanCriteria());
		criteriaList.add(new StringCriteria());
		criteriaList.add(new DateCriteria());
		return criteriaList;
	}

}
