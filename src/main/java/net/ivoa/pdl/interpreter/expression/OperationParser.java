package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import exeptions.InvalidParameterException;

import CommonsObjects.GeneralParameter;
import CommonsObjects.GeneralParameterAlgebra;

import net.ivoa.parameter.model.Operation;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

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
			if (this.operation.getOperationType().equalsIgnoreCase("SCALAR")) {
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
			if (this.operation.getOperationType().equalsIgnoreCase("PLUS")
					|| this.operation.getOperationType().equalsIgnoreCase(
							"MINUS")
					|| this.operation.getOperationType().equalsIgnoreCase(
							"MULTIPLY")
					|| this.operation.getOperationType().equalsIgnoreCase(
							"DIVIDE")) {
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
			GeneralParameter a, GeneralParameter b, String operationType)
			throws InvalidExpression, InvalidParameterException {
		if (operationType.equalsIgnoreCase("PLUS")) {
			return GeneralParameterAlgebra.getInstance().sum(a, b);
		}
		if (operationType.equalsIgnoreCase("MINUS")) {
			return GeneralParameterAlgebra.getInstance().substraction(a, b);
		}
		if (operationType.equalsIgnoreCase("MULTIPLY")) {
			return GeneralParameterAlgebra.getInstance().multiplication(a, b);
		}
		if (operationType.equalsIgnoreCase("DIVIDE")) {
			return GeneralParameterAlgebra.getInstance().division(a, b);
		}
		throw new InvalidExpression("Invalid Operation");
	}

}
