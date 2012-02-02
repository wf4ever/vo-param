package net.ivoa.pdl.interpreter.expression;

import java.util.List;

import CommonsObjects.GeneralParameter;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public abstract class ExpressionParser {
	
	private ExpressionParser parser;
	
	public abstract List<GeneralParameter> processExpression() throws InvalidExpression, InvalidParameterException;
	
	public List<GeneralParameter> parse() throws InvalidExpression, InvalidParameterException{
		return parser.processExpression();
	}
	
}
