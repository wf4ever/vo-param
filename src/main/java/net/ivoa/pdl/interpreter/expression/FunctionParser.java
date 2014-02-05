package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.Function;
import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class FunctionParser extends ExpressionParser {

	private Function exp;

        FunctionParser(Function exp) {
		super();
		this.exp = exp;
	}

	@Override
	public List<GeneralParameter> parse() throws InvalidExpression,
			InvalidParameterException {

		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();

		List<GeneralParameter> containedVectorExpression = ExpressionParserFactory
				.getInstance().buildParser(exp.getExpression()).parse();

		// If the function is the size of the contained expression
		if (exp.getFunctionName().toString().equalsIgnoreCase("SIZE")) {
			toReturn.add(GeneralParameterAlgebra.getInstance().size(
					containedVectorExpression));
			return toReturn;
		}
		// If the function is the sum of the vector
		if (exp.getFunctionName().toString().equalsIgnoreCase("SUM")) {
			toReturn.add(GeneralParameterAlgebra.getInstance().sum(
					containedVectorExpression));
		}
		// If the function is the product of the vector
		if (exp.getFunctionName().toString().equalsIgnoreCase("PRODUCT")) {
			toReturn.add(GeneralParameterAlgebra.getInstance().product(
					containedVectorExpression));
		}

		// In all the other cases
		for (int i = 0; i < containedVectorExpression.size(); i++) {
			toReturn.add(getResultAccordingToFunctionType(
					containedVectorExpression.get(i), exp.getFunctionName()
							.toString()));
		}
		return toReturn;
	}

	private GeneralParameter getResultAccordingToFunctionType(
			GeneralParameter a, String functionType) throws InvalidExpression {
		if (functionType.equalsIgnoreCase("ABS")) {
			return GeneralParameterAlgebra.getInstance().absoluteValue(a);
		}
		if (functionType.equalsIgnoreCase("SIN")) {
			return GeneralParameterAlgebra.getInstance().sinus(a);
		}
		if (functionType.equalsIgnoreCase("COS")) {
			return GeneralParameterAlgebra.getInstance().cosinus(a);
		}
		if (functionType.equalsIgnoreCase("TAN")) {
			return GeneralParameterAlgebra.getInstance().tangent(a);
		}
		if (functionType.equalsIgnoreCase("ASIN")) {
			return GeneralParameterAlgebra.getInstance().asinus(a);
		}
		if (functionType.equalsIgnoreCase("ACOS")) {
			return GeneralParameterAlgebra.getInstance().acosinus(a);
		}
		if (functionType.equalsIgnoreCase("ATAN")) {
			return GeneralParameterAlgebra.getInstance().atan(a);
		}
		if (functionType.equalsIgnoreCase("EXP")) {
			return GeneralParameterAlgebra.getInstance().exp(a);
		}
		if (functionType.equalsIgnoreCase("LOG")) {
			return GeneralParameterAlgebra.getInstance().log(a);
		}
		throw new InvalidExpression("Invalid function");
	}

}
