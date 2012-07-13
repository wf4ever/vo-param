package net.ivoa.pdl.interpreter.criterion;

import net.ivoa.parameter.model.AbstractCriterion;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.ParenthesisCriterion;
import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class ParenthesisCriterionInterpreter extends
		AbstractCriterionInterpreter {

	public ParenthesisCriterionInterpreter(ParenthesisCriterion criterion) {
		super();
		this.criterion = criterion;
	}

	private ParenthesisCriterion criterion;

	@Override
	public boolean isCriterionSatisfied() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {

		AbstractCriterion containedCriterion = new Criterion(
				this.criterion.getExpression(),
				this.criterion.getConditionType(),
				this.criterion.getLogicalConnector());

		boolean toReturn = CriterionInterpreterFactory.getInstance()
				.buildCriterrionInterpreter(containedCriterion)
				.isCriterionSatisfied();

		// If the logical connector is not null
		if (null != this.criterion.getLogicalConnector()) {
			toReturn = new LogicalConnectorInterpreter(
					this.criterion.getExternalLogicalConnector())
					.interpret(toReturn);
		}
		return toReturn;
	}

}
