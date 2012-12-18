package net.ivoa.pdl.interpreter.condition;

import net.ivoa.parameter.model.Expression;
import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class DefaultValueInterpreter extends ConditionInterpreter{

        
	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {
		return true;
	}

}
