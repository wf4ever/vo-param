package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.ValueSmallerThan;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;
import exeptions.InvalidParameterException;

public class ValueSmallerThanInterpreter extends ConditionInterpreter {

	private ValueSmallerThan condition;

	public ValueSmallerThanInterpreter(ValueSmallerThan condition) {
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
					"In ValueSmallerThan condition: the two vector values have different sizes");
		} else {
			for (int i = 0; i < expression.size(); i++) {
				// At the end of the loop toReturn is true iff all the component
				// of the
				// expression are smaller (or <=) than the value in the limit
				// expression
				toReturn = toReturn
						&& GeneralParameterAlgebra.getInstance()
								.isFirstSmallerThanSecond(expression.get(i),
										limitValues.get(i),
										condition.isReached());
			}
		}
		return toReturn;
	}

}
