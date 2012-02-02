package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;
import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class IsRealInterpreter extends ConditionInterpreter {

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {
		boolean isReal = true;
		List<GeneralParameter> expression = ExpressionParserFactory
				.getInstance().buildParser(exp).parse();

		for (int i = 0; i < expression.size(); i++) {
			isReal = isReal
					&& GeneralParameterAlgebra.getInstance()
							.isGeneralParameterReal(expression.get(i));
		}

		return isReal;
	}
}
