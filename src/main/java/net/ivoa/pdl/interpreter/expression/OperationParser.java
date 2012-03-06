package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.OperationType;
import net.ivoa.parameter.model.ParameterType;

public class OperationParser {

	private Operation operation;

	public OperationParser(Operation operation) {
		super();
		this.operation = operation;
	}

	public List<GeneralParameter> processOperation(
			List<GeneralParameter> firstOperand) throws InvalidExpression,
			InvalidParameterException {

		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();

		List<GeneralParameter> secondOperand = ExpressionParserFactory
				.getInstance().buildParser(this.operation.getExpression())
				.parse();

		if (firstOperand.size() == secondOperand.size()) {
			// If the operation is the scalar product
			if (this.operation.getOperationType() == OperationType.SCALAR) {
				// The variable used for storing the scalar product
				GeneralParameter scalarProductResult = new GeneralParameter(
						"0", ParameterType.INTEGER.toString(),
						"result of scalar product", firstOperand.get(0)
								.getVisitor());
				for (int i = 0; i < firstOperand.size(); i++) {
					// computing the scalar product
					scalarProductResult = GeneralParameterAlgebra.getInstance()
							.sum(scalarProductResult,
									(GeneralParameterAlgebra.getInstance()
											.multiplication(
													firstOperand.get(i),
													secondOperand.get(i))));
				}
				// Adding the scalar product to the list to return
				toReturn.add(scalarProductResult);
				return toReturn;
			}

			// if the operation is a multiplication, a division, a sum or
			// difference
			if (this.operation.getOperationType() == OperationType.PLUS
					|| this.operation.getOperationType() == OperationType.MINUS
					|| this.operation.getOperationType() == OperationType.MULTIPLY
					|| this.operation.getOperationType() == OperationType.DIVIDE) {
				for (int i = 0; i < firstOperand.size(); i++) {
					toReturn.add(getResultAccordingToOperationType(
							firstOperand.get(i), secondOperand.get(i),
							(this.operation.getOperationType())));
				}
				return toReturn;
			}
		}// end of case where the two operands has the same size

		// The first operand is a scalar and the second is a vector
		if (firstOperand.size() == 1 && secondOperand.size() > 1) {
			for (int i = 0; i < secondOperand.size(); i++) {
				toReturn.add(getResultAccordingToOperationType(
						firstOperand.get(0), secondOperand.get(i),
						(this.operation.getOperationType())));
			}
			return toReturn;
		}

		// The first operand is a vector and the second is a scalar
		if (firstOperand.size() > 1 && secondOperand.size() == 1) {
			for (int i = 0; i < firstOperand.size(); i++) {
				toReturn.add(getResultAccordingToOperationType(
						firstOperand.get(i), secondOperand.get(0),
						(this.operation.getOperationType())));
			}
			return toReturn;
		}

		throw new InvalidExpression("Inconsistent operation");
	}

	
	private GeneralParameter getResultAccordingToOperationType(
			GeneralParameter a, GeneralParameter b, OperationType operationType)
			throws InvalidExpression, InvalidParameterException {
	    switch (operationType ){
	    case PLUS:
	        return GeneralParameterAlgebra.getInstance().sum(a, b);

	    case MINUS:
	        return GeneralParameterAlgebra.getInstance().substraction(a, b);

	    case MULTIPLY:
	        return GeneralParameterAlgebra.getInstance().multiplication(a, b);

	    case DIVIDE:
	        return GeneralParameterAlgebra.getInstance().division(a, b);

	    default:
	        throw new InvalidExpression("Invalid Operation");
	       }
	}

}
