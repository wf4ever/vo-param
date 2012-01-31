package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import exeptions.InvalidParameterException;

import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

public abstract class ExpressionWithPowerParser extends ExpressionParser{
	
	public List<GeneralParameter> evaluatePower(
			List<GeneralParameter> userProvidedValue,
			List<GeneralParameter> power) throws InvalidParameterException,
			InvalidExpression{
		
		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();
		
		// If the power is null, no more operation is needed
		// We return the general parameter 
		if (null == power || power.size() < 1) {
			toReturn = userProvidedValue;
			return toReturn;
		}

		if (userProvidedValue.size() == power.size()) {
			for (int i = 0; i < power.size(); i++) {
				toReturn.add(GeneralParameterAlgebra.getInstance().power(
						userProvidedValue.get(i), power.get(i)));
			}
			return toReturn;
		}

		if (userProvidedValue.size() != power.size()) {
			if (power.size() == 1) {
				for (int i = 0; i < power.size(); i++) {
					toReturn.add(GeneralParameterAlgebra.getInstance().power(
							userProvidedValue.get(i), power.get(0)));
				}
				return toReturn;
			} else {
				throw new InvalidExpression(
						"Inconsistent power operation");
			}
		}
		throw new InvalidExpression(
				"Inconsistent power operation");
	}

	
}
