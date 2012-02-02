package net.ivoa.pdl.interpreter.criterion;

import net.ivoa.parameter.model.And;
import net.ivoa.parameter.model.LogicalConnector;
import net.ivoa.parameter.model.Or;
import exeptions.InvalidCondition;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class LogicalConnectorInterpreter {

	private LogicalConnector connector;

	public LogicalConnectorInterpreter(LogicalConnector connector) {
		super();
		this.connector = connector;
	}

	public boolean interpret(boolean firstCriterionResult)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition, InvalidCriterion {

		boolean secondCriterionResult = CriterionInterpreterFactory
				.getInstance()
				.buildCriterrionInterpreter(this.connector.getCriterion())
				.isCriterionSatisfied();

		return getResultAccordingToConnectorType(firstCriterionResult, secondCriterionResult);
	}

	private boolean getResultAccordingToConnectorType(boolean a, boolean b)
			throws InvalidCriterion {
		if (this.connector.getClass() == And.class) {
			return (a && b);
		}
		if (this.connector.getClass() == Or.class) {
			return (a || b);
		}
		throw new InvalidCriterion("Invalid LogicalConnector "
				+ this.connector.getClass());
	}
}
