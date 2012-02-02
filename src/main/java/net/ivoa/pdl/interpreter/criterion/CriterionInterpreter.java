package net.ivoa.pdl.interpreter.criterion;

import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.pdl.interpreter.condition.ConditionInterpreterFactory;

public class CriterionInterpreter extends AbstractCriterionInterpreter {

	public CriterionInterpreter(Criterion criterion) {
		super();
		this.criterion = criterion;
	}

	private Criterion criterion;

	@Override
	public boolean isCriterionSatisfied() throws InvalidExpression,
			InvalidParameterException, InvalidCondition, InvalidCriterion {
		
		// First let us interpret the condition contained into the criterion
		boolean toReturn = ConditionInterpreterFactory.getInstance()
				.buildConditionInterpreter(criterion.getConditionType())
				.isConditionVerified(this.criterion.getExpression());
		
		// If the logical connector is not null
		if (null != this.criterion.getLogicalConnector()) {
			// We evaluate the result of the condition and the condition contained into 
			// the logical connector 
			toReturn = new LogicalConnectorInterpreter(
					this.criterion.getLogicalConnector()).interpret(toReturn);
		}
		return toReturn;
	}

}
