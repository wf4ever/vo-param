package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.Operation;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

public class OperationParser {

	private Operation operation;

	public OperationParser(Operation operation) {
		super();
		this.operation = operation;
	}

	public List<String> processOperation(List<String> firstOperand)
			throws InvalidExpression {

		List<String> toReturn = new ArrayList<String>();

		List<String> secondOperand = ExpressionParserFactory.getInstance()
				.buildParser(this.operation.getExpression()).parse();

		if (firstOperand.size() == secondOperand.size()) {
			if (this.operation.getOperationType().equalsIgnoreCase("SCALAR")) {
				String result="(";
				for(int i=0; i<firstOperand.size();i++){
					result = result + firstOperand.get(i)+"*"+secondOperand.get(i);
				}
				result = result+")";
				toReturn.add(result);
				
			}
		}

		return toReturn;
	}

}
