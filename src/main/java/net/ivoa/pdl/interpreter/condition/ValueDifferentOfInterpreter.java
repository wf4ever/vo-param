package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.ValueDifferentOf;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class ValueDifferentOfInterpreter extends ConditionInterpreter {

	private ValueDifferentOf condition;

	public ValueDifferentOfInterpreter(ValueDifferentOf condition) {
		super();
		this.condition = condition;
	}

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {
		boolean areExpressionAndValueEquals=true;

		List<GeneralParameter> expression = ExpressionParserFactory
				.getInstance().buildParser(exp).parse();

		List<GeneralParameter> referenceValue = ExpressionParserFactory
				.getInstance().buildParser(this.condition.getValue()).parse();

		if (expression.size() != referenceValue.size()) {
			throw new InvalidCondition(
					"In ValueDifferentOf condition: the two vector values have different sizes");
		} else {
			for (int i = 0; i < expression.size(); i++) {
				areExpressionAndValueEquals = areExpressionAndValueEquals
						&& GeneralParameterAlgebra.getInstance()
								.areGeneralParamtersEqual(expression.get(i),
										referenceValue.get(i));
			}
		}

		return !areExpressionAndValueEquals;
	}

}
