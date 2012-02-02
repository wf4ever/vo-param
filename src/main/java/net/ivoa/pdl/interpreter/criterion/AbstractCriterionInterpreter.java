package net.ivoa.pdl.interpreter.criterion;

import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public abstract class AbstractCriterionInterpreter {
	public abstract boolean isCriterionSatisfied() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion;
}
