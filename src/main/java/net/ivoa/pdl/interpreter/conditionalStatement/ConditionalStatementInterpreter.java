package net.ivoa.pdl.interpreter.conditionalStatement;

import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public abstract class ConditionalStatementInterpreter {
	// tell if the statement is valid
	public abstract boolean isValidStatement() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion;
	
	// tell if the statement is activated
	public abstract boolean isStatementSwitched() throws InvalidExpression, InvalidParameterException, InvalidCondition, InvalidCriterion; 
}
