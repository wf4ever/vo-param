package net.ivoa.pdl.interpreter.condition;

import exeptions.InvalidParameterException;
import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

public abstract class ConditionInterpreter {

	public abstract boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition;
}
