package net.ivoa.pdl.interpreter.expression;

import java.util.List;

import CommonsObjects.GeneralParameter;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public abstract class ExpressionParser {
	public abstract List<GeneralParameter> parse() throws InvalidExpression, InvalidParameterException;
}
