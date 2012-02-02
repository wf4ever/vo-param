package net.ivoa.pdl.interpreter.criterion;

import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCriterion;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import exeptions.InvalidParameterException;

public abstract class AbstractCriterionInterpreter {
	public abstract boolean isCriterionSatisfied() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion;
}
