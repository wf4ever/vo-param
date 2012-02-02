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

	@Override
	public boolean isValidStatement() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {
		// The always statement is valid if the contained criterion is verified

		return CriterionInterpreterFactory
				.getInstance()
				.buildCriterrionInterpreter(
						this.statement.getAlways().getCriterion())
				.isCriterionSatisfied();
	}

}
