package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class AtomicParameterExpressionParser extends ExpressionParser {

	private AtomicParameterExpression exp;

	public AtomicParameterExpressionParser(AtomicParameterExpression exp) {
		this.exp = exp;
	}

	@Override
	public List<String> processExpression() throws InvalidExpression {

		List<String> toReturn = new ArrayList<String>();

		SingleParameter parameter = Utilities.getInstance()
				.getParameterFromReference(this.exp.getParameterRef());

		List<String> power = ExpressionParserFactory.getInstance()
				.buildParser(this.exp.getPower()).parse();

		toReturn = withoutOperationParser(parameter, power);

		if (null != this.exp.getOperation()) {
			//List<String> operationPart = new OperationParser(
			//		this.exp.getOperation()).processOperation();
			
			
			
		}

		return toReturn;
	}

	private Integer getParameterDimension(SingleParameter parameter)
			throws InvalidExpression {
		List<String> dimensions = ExpressionParserFactory.getInstance()
				.buildParser(parameter.getDimension()).parse();
		if (dimensions.size() > 1) {
			throw new InvalidExpression("Dimension must be a scalar value");
		} else {
			try {
				return new IntegerInterpreter().interpreate(dimensions).get(0);
			} catch (Exception e) {
				throw new InvalidExpression(
						"Dimension must be an integer scalar value");
			}
		}
	}

	private List<String> withoutOperationParser(SingleParameter parameter,
			List<String> power) throws InvalidExpression {

		List<String> userProvidedValue = Utilities.getInstance()
				.getuserProvidedValuesForParameter(parameter);

		Integer parameterDimension = getParameterDimension(parameter);

		if (userProvidedValue.size() != parameterDimension) {
			throw new InvalidExpression(
					"Incompatible user provided dimension for parameter "
							+ parameter.getName());
		}

		List<String> toReturn = new ArrayList<String>();

		if (parameterDimension == power.size()) {
			for (int i = 0; i < power.size(); i++) {
				toReturn.add("Math.pow(" + userProvidedValue.get(i) + ","
						+ power.get(i) + ")");
			}
		}

		if (parameterDimension != power.size()) {
			if (power.size() == 1) {
				for (int i = 0; i < power.size(); i++) {
					toReturn.add("Math.pow(" + userProvidedValue.get(i) + ","
							+ power.get(0) + ")");
				}
			} else {
				throw new InvalidExpression(
						"Inconsistent power operation for parameter "
								+ parameter.getName());
			}
		}
		return toReturn;
	}

}
