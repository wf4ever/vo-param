package net.ivoa.pdl.interpreter.expression;

import java.util.List;

import exeptions.InvalidParameterException;

import CommonsObjects.GeneralParameter;

import net.ivoa.parameter.model.Expression;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

public abstract class ExpressionParser {
	
	private ExpressionParser parser;
	
	public abstract List<GeneralParameter> processExpression() throws InvalidExpression, InvalidParameterException;
	
	public List<GeneralParameter> parse() throws InvalidExpression, InvalidParameterException{
		return parser.processExpression();
	}
	
}
