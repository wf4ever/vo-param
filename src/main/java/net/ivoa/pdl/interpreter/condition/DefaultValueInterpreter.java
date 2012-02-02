package net.ivoa.pdl.interpreter.condition;

import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class DefaultValueInterpreter extends ConditionInterpreter{

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {
		return true;
	}

}
