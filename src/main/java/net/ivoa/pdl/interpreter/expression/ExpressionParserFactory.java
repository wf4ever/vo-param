package net.ivoa.pdl.interpreter.expression;

import net.ivoa.parameter.model.AtomicConstantExpression;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.Expression;

public class ExpressionParserFactory {
	private static final ExpressionParserFactory instance = new ExpressionParserFactory();

	public static ExpressionParserFactory getInstance() {
		return instance;
	}

	private ExpressionParserFactory() {
	}

	public ExpressionParser buildParser(Expression exp) {
		if(exp.getClass() == AtomicConstantExpression.class){
			return new AtomicConstantExpressionParser((AtomicConstantExpression) exp);
		}
		
		if (exp.getClass() == AtomicParameterExpression.class) {
			return new AtomicParameterExpressionParser((AtomicParameterExpression) exp);
		} else {
			return null;
		}

	}
}
