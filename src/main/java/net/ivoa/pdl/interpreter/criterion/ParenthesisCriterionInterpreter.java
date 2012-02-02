package net.ivoa.pdl.interpreter.criterion;

import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.ParenthesisCriterion;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCriterion;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class ParenthesisCriterionInterpreter extends AbstractCriterionInterpreter {
	
	public ParenthesisCriterionInterpreter(ParenthesisCriterion criterion) {
		super();
		this.criterion = criterion;
	}

	private ParenthesisCriterion criterion;	
	
	@Override
	public boolean isCriterionSatisfied() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {
		
		Criterion containedCriterion = new Criterion();
		containedCriterion.withExpression(this.criterion.getExpression());
		containedCriterion.withConditionType(this.criterion.getConditionType());
		containedCriterion.withLogicalConnector(this.criterion.getLogicalConnector());
		
		boolean toReturn = new CriterionInterpreter(containedCriterion).isCriterionSatisfied();
		
		// If the logical connector is not null
		if (null != this.criterion.getLogicalConnector()) {
			toReturn = new LogicalConnectorInterpreter(
					this.criterion.getExternalLogicalConnector()).interpret(toReturn);
		}
		return toReturn;
	}

}
