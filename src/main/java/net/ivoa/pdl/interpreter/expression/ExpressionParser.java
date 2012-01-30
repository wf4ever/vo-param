package net.ivoa.pdl.interpreter.expression;

import java.util.List;

import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

public abstract class ExpressionParser {
	
	private ExpressionParser parser;
	
	public abstract List<String> processExpression() throws InvalidExpression;
	
	public List<String> parse() throws InvalidExpression{
		return parser.processExpression();
	}
	
}
