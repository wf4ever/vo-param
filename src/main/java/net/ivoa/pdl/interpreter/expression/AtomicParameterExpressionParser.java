package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidExpression;
import net.ivoa.pdl.interpreter.utilities.Utilities;
import CommonsObjects.GeneralParameter;
import exeptions.InvalidParameterException;

public class AtomicParameterExpressionParser extends AtomicExpressionParser {

	private AtomicParameterExpression exp;

	public AtomicParameterExpressionParser(AtomicParameterExpression exp) {
		this.exp = exp;
	}

	@Override
	public List<GeneralParameter> processExpression() throws InvalidExpression,
			InvalidParameterException {

		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();

		SingleParameter parameter = Utilities.getInstance()
				.getParameterFromReference(this.exp.getParameterRef());

		// Defining the list of GeneralParameter for Power expression
		List<GeneralParameter> power=null;
		// If the power expression is not null we parse it
		if (null != this.exp.getPower()) {
			power = ExpressionParserFactory.getInstance()
					.buildParser(this.exp.getPower()).parse();
		}

		// The interpretation of the expression without the operation part
		toReturn = withoutOperationParser(parameter, power);

		if (null != this.exp.getOperation()) {
			// The interpretation of the expression by considering the
			// expression part
			toReturn = new OperationParser(this.exp.getOperation())
					.processOperation(toReturn);
		}

		return toReturn;
	}

	private Integer getParameterDimension(SingleParameter parameter)
			throws InvalidExpression, InvalidParameterException {
		List<GeneralParameter> dimensions = ExpressionParserFactory
				.getInstance().buildParser(parameter.getDimension()).parse();
		if (dimensions.size() > 1) {
			throw new InvalidExpression("Dimension must be a scalar value");
		} else {
			try {
				return Integer.parseInt(dimensions.get(0).getValue());
			} catch (Exception e) {
				throw new InvalidExpression(
						"Dimension must be an integer scalar value");
			}
		}
	}

	private List<GeneralParameter> withoutOperationParser(
			SingleParameter parameter, List<GeneralParameter> power)
			throws InvalidExpression, InvalidParameterException {

		List<GeneralParameter> userProvidedValue = Utilities.getInstance()
				.getuserProvidedValuesForParameter(parameter);

		Integer parameterDimension = getParameterDimension(parameter);

		if (userProvidedValue.size() != parameterDimension) {
			throw new InvalidExpression(
					"Incompatible user provided dimension for parameter "
							+ parameter.getName());
		}
		return this.evaluatePower(userProvidedValue, power);
	}

}
