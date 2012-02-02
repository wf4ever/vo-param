package net.ivoa.pdl.interpreter.condition;

import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;
import net.ivoa.parameter.model.Expression;

public abstract class ConditionInterpreter {

	public abstract boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition;
}
