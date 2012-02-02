package net.ivoa.pdl.interpreter.condition;

import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.ValueInRange;
import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class ValueInRangeInterpreter extends ConditionInterpreter {

	private ValueInRange condition;

	public ValueInRangeInterpreter(ValueInRange condition) {
		super();
		this.condition = condition;
	}

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {
		boolean toReturn = true;

		toReturn = new ValueLargerThanInterpreter(condition.getInf())
				.isConditionVerified(exp)
				&& new ValueSmallerThanInterpreter(condition.getSup())
						.isConditionVerified(exp);

		return toReturn;
	}

}
