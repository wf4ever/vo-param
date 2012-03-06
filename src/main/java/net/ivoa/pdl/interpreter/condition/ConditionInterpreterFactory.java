package net.ivoa.pdl.interpreter.condition;

import exeptions.InvalidCondition;
import net.ivoa.parameter.model.AbstractCondition;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.DefaultValue;
import net.ivoa.parameter.model.IsInteger;
import net.ivoa.parameter.model.IsNull;
import net.ivoa.parameter.model.IsReal;
import net.ivoa.parameter.model.ValueDifferentFrom;
import net.ivoa.parameter.model.ValueInRange;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.parameter.model.ValueSmallerThan;

public class ConditionInterpreterFactory {
	private static final ConditionInterpreterFactory instance = new ConditionInterpreterFactory();

	public static ConditionInterpreterFactory getInstance() {
		return instance;
	}

	private ConditionInterpreterFactory() {
	}

	public ConditionInterpreter buildConditionInterpreter(
			AbstractCondition condition) throws InvalidCondition {
		if (condition.getClass() == ValueLargerThan.class) {
			return new ValueLargerThanInterpreter((ValueLargerThan) condition);
		}
		if (condition.getClass() == ValueSmallerThan.class) {
			return new ValueSmallerThanInterpreter((ValueSmallerThan) condition);
		}
		if (condition.getClass() == ValueInRange.class) {
			return new ValueInRangeInterpreter((ValueInRange) condition);
		}
		if (condition.getClass() == ValueDifferentFrom.class) {
			return new ValueDifferentFromInterpreter((ValueDifferentFrom) condition);
		}
		if (condition.getClass() == BelongToSet.class) {
			return new BelongToSetInterpreter((BelongToSet) condition);
		}
		if(condition.getClass() == DefaultValue.class){
			return new DefaultValueInterpreter();
		}
		if(condition.getClass() == IsInteger.class ){
			return new IsIntegerInterpreter();
		}
		if (condition.getClass() == IsReal.class){
			return new IsRealInterpreter();
		}
		if (condition.getClass() == IsNull.class){
			return new IsNullInterpreter();
		}
		throw new InvalidCondition("Non Handled condition "+ condition.getClass().toString());
	}

}
