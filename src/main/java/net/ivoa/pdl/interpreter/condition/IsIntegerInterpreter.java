package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class IsIntegerInterpreter extends ConditionInterpreter {

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {
		boolean isInteger = true;
		List<GeneralParameter> expression = ExpressionParserFactory
				.getInstance().buildParser(exp).parse();

		for (int i = 0; i < expression.size(); i++) {
			isInteger = isInteger
					&& GeneralParameterAlgebra.getInstance()
							.isGeneralParameterInteger(expression.get(i));
		}

		return isInteger;
	}

}
