package net.ivoa.pdl.interpreter.condition;

import java.util.List;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCondition;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class BelongToSetInterpreter extends ConditionInterpreter {

	private BelongToSet condition;

	public BelongToSetInterpreter(BelongToSet condition) {
		super();
		this.condition = condition;
	}

	@Override
	public boolean isConditionVerified(Expression exp)
			throws InvalidExpression, InvalidParameterException,
			InvalidCondition {

		List<GeneralParameter> expression = ExpressionParserFactory
				.getInstance().buildParser(exp).parse();

		List<Expression> setOfValues = this.condition.getValue();

		for (Expression value : setOfValues) {
			List<GeneralParameter> valueInterpetation = ExpressionParserFactory
					.getInstance().buildParser(value).parse();
			boolean areExpressionAndValueEquals=true;
			for (int i = 0; i < expression.size(); i++) {
				areExpressionAndValueEquals = areExpressionAndValueEquals
						&& GeneralParameterAlgebra.getInstance()
								.areGeneralParamtersEqual(expression.get(i),
										valueInterpetation.get(i));
			}
			if(areExpressionAndValueEquals){
				return true;
			}
		}
		return false;
	}

}
