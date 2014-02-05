package net.ivoa.pdl.interpreter.expression;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.ParenthesisContent;
import CommonsObjects.GeneralParameter;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class ParenthesisContentParser extends ExpressionWithPowerParser {

	private ParenthesisContent exp;

       ParenthesisContentParser(ParenthesisContent exp) {
		super();
		this.exp = exp;
	}

	@Override
	public List<GeneralParameter> parse() throws InvalidExpression,
			InvalidParameterException {
		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();

		List<GeneralParameter> priorityExpression = ExpressionParserFactory
				.getInstance().buildParser(exp.getExpression()).parse();

		List<GeneralParameter> power = null;
		if (null != this.exp.getPower()) {
			power = ExpressionParserFactory.getInstance()
					.buildParser(this.exp.getPower()).parse();
		}

		toReturn = this.evaluatePower(priorityExpression, power);

		if (null != this.exp.getOperation()) {
			// The interpretation of the expression by considering the
			// expression part
			toReturn = new OperationParser(this.exp.getOperation())
					.processOperation(toReturn);
		}

		return toReturn;
	}

}
