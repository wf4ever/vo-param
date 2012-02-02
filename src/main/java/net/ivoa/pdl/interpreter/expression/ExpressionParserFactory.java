package net.ivoa.pdl.interpreter.expression;

import exeptions.InvalidExpression;
import net.ivoa.parameter.model.AtomicConstantExpression;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.Function;
import net.ivoa.parameter.model.FunctionExpression;
import net.ivoa.parameter.model.ParenthesisContent;

public class ExpressionParserFactory {
	private static final ExpressionParserFactory instance = new ExpressionParserFactory();

	public static ExpressionParserFactory getInstance() {
		return instance;
	}

	private ExpressionParserFactory() {
	}

	public ExpressionParser buildParser(Expression exp) throws InvalidExpression {
		// Case of a function 
		if(exp.getClass() == Function.class){
			return new FunctionParser((Function) exp);
		}
		
		// Case of a ParenthesisCOntent 
		if(exp.getClass() == ParenthesisContent.class){
			return new ParenthesisContentParser((ParenthesisContent) exp);
		}
		
		// Case of a functionExpression
		if(exp.getClass() == FunctionExpression.class){
			return new FunctionExpressionParser((FunctionExpression) exp);
		}
		
		// Case of an Atomic Constant expression
		if(exp.getClass() == AtomicConstantExpression.class){
			return new AtomicConstantExpressionParser((AtomicConstantExpression) exp);
		}
		
		// Case of an Atomic Parameter Expression
		if (exp.getClass() == AtomicParameterExpression.class) {
			return new AtomicParameterExpressionParser((AtomicParameterExpression) exp);
		} else {
			throw new InvalidExpression("could not build appropriate parser");
		}

	}
}
