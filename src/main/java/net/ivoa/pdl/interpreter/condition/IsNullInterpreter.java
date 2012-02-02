package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;
import CommonsObjects.GeneralParameter;
import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class IsNullInterpreter extends ConditionInterpreter {

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {

		AtomicParameterExpression expression = null;
		try {
			expression = (AtomicParameterExpression) exp;
		} catch (Exception e) {
			throw new InvalidCondition(
					"IsNull condition works only with AtomicParameterExpression");
		}

		SingleParameter param = Utilities.getInstance()
				.getParameterFromReference(expression.getParameterRef());

		List<GeneralParameter> vectorParams = Utilities.getInstance()
				.getuserProvidedValuesForParameter(param);
		
		if(null==vectorParams || vectorParams.size()<1){
			return true;
		}
		boolean allValueAreNull=true;
		for(int i=0;i<vectorParams.size();i++){
			allValueAreNull = allValueAreNull && (null==vectorParams.get(i));
		}
		return allValueAreNull;
	}

}
