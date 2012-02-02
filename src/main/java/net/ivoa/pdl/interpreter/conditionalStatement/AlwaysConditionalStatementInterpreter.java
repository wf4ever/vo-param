package net.ivoa.pdl.interpreter.conditionalStatement;

import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.pdl.interpreter.criterion.CriterionInterpreterFactory;

public class AlwaysConditionalStatementInterpreter extends
		ConditionalStatementInterpreter {

	public AlwaysConditionalStatementInterpreter(
			AlwaysConditionalStatement statement) {
		super();
		this.statement = statement;
		this.isStatementSwitched = true;
	}

	private AlwaysConditionalStatement statement;

	// tell if the statement is switched
	private boolean isStatementSwitched;
	
	@Override
	public boolean isStatementSwitched() {
		return isStatementSwitched;
	}
	
	// tell if the statement is valid, i.e. verified
	private boolean isStatementValid;

	@Override
	public boolean isValidStatement() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {
		// The always statement is valid if the containes criterion is verified
		this.isStatementValid = CriterionInterpreterFactory
				.getInstance()
				.buildCriterrionInterpreter(
						this.statement.getAlways().getCriterion())
				.isCriterionSatisfied();

		return this.isStatementValid;
	}

}
