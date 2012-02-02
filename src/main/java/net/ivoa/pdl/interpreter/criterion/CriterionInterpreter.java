package net.ivoa.pdl.interpreter.criterion;

import exeptions.InvalidParameterException;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.pdl.interpreter.condition.ConditionInterpreterFactory;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCriterion;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

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
