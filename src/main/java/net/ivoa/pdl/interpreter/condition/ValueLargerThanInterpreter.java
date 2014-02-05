package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.ValueLargerThan;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;

public class ValueLargerThanInterpreter extends ConditionInterpreter {

	private ValueLargerThan condition;

	ValueLargerThanInterpreter(ValueLargerThan condition) {
		super();
		this.condition = condition;
	}

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {

		List<GeneralParameter> expression = ExpressionParserFactory
				.getInstance().buildParser(exp).parse();
		List<GeneralParameter> limitValues = ExpressionParserFactory
				.getInstance().buildParser(condition.getValue()).parse();

		boolean toReturn = true;

		if (expression.size() != limitValues.size()) {
			throw new InvalidCondition(
					"In ValueLargerThan condition: the two vector values have different sizes");
		} else {
			for (int i = 0; i < expression.size(); i++) {
				// At the end of the loop toReturn is true iff all the component of the 
				// expression are greater (or >=) than the value in the limit expression
				toReturn = toReturn
						&& GeneralParameterAlgebra.getInstance()
								.isFirstGreaterThanSecond(expression.get(i),
										limitValues.get(i), condition.isReached());
			}
		}
		return toReturn;
	}

}
