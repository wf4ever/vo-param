package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;

public class IntegerInterpreter extends Interpreter {

	List<Integer> interpreate(List<String> vectorExpression)
			throws InvalidExpression {
		List<Integer> toReturn = new ArrayList<Integer>();
		for (int i = 0; i < vectorExpression.size(); i++) {
			try {
				Integer temp = Integer.parseInt(vectorExpression.get(i));
				
				//verify that the value is a integer
				Double tempD = Double.parseDouble(vectorExpression.get(i));
				double difference = tempD-temp;
				if(Math.abs(difference)>0.001){
					throw new InvalidExpression(
					"Dimension must be an integer scalar value");
				}
				toReturn.add(temp);
			} catch (Exception e) {
				throw new InvalidExpression(
						"Dimension must be an integer scalar value");
			}
		}
		return toReturn;
	}
}
