package net.ivoa.pdl.interpreter.conditionalStatement;

import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.pdl.interpreter.criterion.CriterionInterpreterFactory;
import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class IfThenConditionalStatementInterpreter extends
		ConditionalStatementInterpreter {

	private IfThenConditionalStatement statement;

	public IfThenConditionalStatementInterpreter(
			IfThenConditionalStatement statement) {
		super();
		this.statement = statement;
	}

	@Override
	public boolean isValidStatement() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {
		boolean isStatementSwitched = this.isStatementSwitched();
		if (!isStatementSwitched) {
			// If the statement is not switched it is always validate, since the
			// contained
			// condition has no matter
			return true;
		} else {
			boolean thenConditionValid = CriterionInterpreterFactory
					.getInstance()
					.buildCriterrionInterpreter(
							this.statement.getThen().getCriterion())
					.isCriterionSatisfied();
			// If the statement is switched it is valid only if the criterion
			// contained into the then condition is valid
			return thenConditionValid;
		}
	}

	@Override
	public boolean isStatementSwitched() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {
		return CriterionInterpreterFactory
				.getInstance()
				.buildCriterrionInterpreter(
						this.statement.getIf().getCriterion())
				.isCriterionSatisfied();
	}

}
