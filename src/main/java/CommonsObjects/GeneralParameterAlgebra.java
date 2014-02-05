package CommonsObjects;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.ParameterType;
import visitors.GeneralParameterVisitor;
import visitors.Ivisitor;
import exeptions.InvalidCondition;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class GeneralParameterAlgebra {

	private static final GeneralParameterAlgebra instance = new GeneralParameterAlgebra();

	public static GeneralParameterAlgebra getInstance() {
		return instance;
	}

	/**
	 * This parameter express the sense of the equality for numerical types :
	 * two numbers a and b are considered equals if |a-b|<epsilon
	 */
	public static double epsilon = 0.00001;//0.0000001;

	private GeneralParameterAlgebra() {
	}

	public GeneralParameter sum(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If a is an integer
		if (a.getType() == ParameterType.INTEGER) {
			// If b is an integer too
			if (b.getType() == ParameterType.INTEGER) {
				Integer Result = Integer.parseInt(a.getValue())
						+ Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER,
						"result of sum " + a.getDescription() + "+"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real
			if (b.getType() == ParameterType.REAL) {
				Double Result = Integer.parseInt(a.getValue())
						+ Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL,
						"result of sum " + a.getDescription() + "+"
								+ b.getDescription(), a.getVisitor());
			}
		}

		// if b is integer
		if (b.getType() == ParameterType.INTEGER) {
			return sum(b, a);
		}

		// if a and b are real
		if (a.getType() == ParameterType.REAL
				&& b.getType() == ParameterType.REAL) {
			Double Result = Double.parseDouble(a.getValue())
					+ Double.parseDouble(b.getValue());
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL, "result of sum "
							+ a.getDescription() + "+" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "+" + b.getDescription());
	}

	public GeneralParameter multiplication(GeneralParameter a,
			GeneralParameter b) throws InvalidParameterException {
		// If a is an integer
		if (a.getType() == ParameterType.INTEGER) {
			// If b is an integer too
			if (b.getType() == ParameterType.INTEGER) {
				Integer Result = Integer.parseInt(a.getValue())
						* Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER,
						"result of product " + a.getDescription() + "*"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real
			if (b.getType() == ParameterType.REAL) {
				Double Result = Integer.parseInt(a.getValue())
						* Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL,
						"result of product " + a.getDescription() + "*"
								+ b.getDescription(), a.getVisitor());
			}
		}

		// if b is integer
		if (b.getType() == ParameterType.INTEGER) {
			return multiplication(b, a);
		}

		// if a and b are real
		if (a.getType() == ParameterType.REAL
				&& b.getType() == ParameterType.REAL) {
			Double Result = Double.parseDouble(a.getValue())
					* Double.parseDouble(b.getValue());
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL, "result of product "
							+ a.getDescription() + "*" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "*" + b.getDescription());
	}

	public GeneralParameter substraction(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If a is an integer
		if (a.getType() == ParameterType.INTEGER) {
			// If b is an integer too
			if (b.getType() == ParameterType.INTEGER) {
				Integer Result = Integer.parseInt(a.getValue())
						- Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER,
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real
			if (b.getType() == ParameterType.REAL) {
				Double Result = Integer.parseInt(a.getValue())
						- Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL,
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}
		}

		// If a is real
		if (a.getType() == ParameterType.REAL) {
			// If b is an integer
			if (b.getType() == ParameterType.INTEGER) {
				Double Result = Double.parseDouble(a.getValue())
						- Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER,
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real too
			if (b.getType() == ParameterType.REAL) {
				Double Result = Double.parseDouble(a.getValue())
						- Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL,
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "-" + b.getDescription());
	}

	public GeneralParameter division(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If (a is real or integer) and (b is real or integer)
		if ((a.getType() == ParameterType.REAL || a
				.getType() == ParameterType.INTEGER)
				&& (b.getType() == ParameterType.REAL || b
						.getType() == 
								ParameterType.INTEGER)) {
			Double Result = Double.parseDouble(a.getValue())
					/ Double.parseDouble(b.getValue());
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL, "result of division "
							+ a.getDescription() + "/" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "/" + b.getDescription());
	}

	public GeneralParameter power(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If (a is real or integer) and (b is real or integer)
		if ((a.getType() == ParameterType.REAL || a
				.getType() == ParameterType.INTEGER)
				&& (b.getType() == ParameterType.REAL || b
						.getType() == 
								ParameterType.INTEGER)) {
			Double Result = Math.pow(Double.parseDouble(a.getValue()),
					Double.parseDouble(b.getValue()));
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL, "result of power "
							+ a.getDescription() + "powered by"
							+ b.getDescription(), a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "powered by" + b.getDescription());
	}

	public GeneralParameter absoluteValue(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.abs(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "absolute value of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute abs of non numerical type");
		}
	}

	public GeneralParameter sinus(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.sin(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "sinus of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute sinus of non numerical type");
		}
	}

	public GeneralParameter cosinus(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.cos(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "cosinus of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute cosinus of non numerical type");
		}
	}

	public GeneralParameter tangent(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.tan(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "tan of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute tan of non numerical type");
		}
	}

	public GeneralParameter asinus(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.asin(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "asinus of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute asinus of non numerical type");
		}
	}

	public GeneralParameter acosinus(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.acos(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "acos of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute acos of non numerical type");
		}
	}

	public GeneralParameter atan(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.atan(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "atan of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute atan of non numerical type");
		}
	}

	public GeneralParameter exp(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.exp(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "exp of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute exp of non numerical type");
		}
	}

	public GeneralParameter log(GeneralParameter param)
			throws InvalidExpression {
		if (GeneralParameterAlgebra.getInstance().isNumericalGeneralParameter(
				param)) {
			Double value = Math.log(Double.parseDouble(param.getValue()));
			return new GeneralParameter(value.toString(),
					ParameterType.REAL, "log of "
							+ param.getDescription(), param.getVisitor());
		} else {
			throw new InvalidExpression(
					"Cannot compute log of non numerical type");
		}
	}

	public GeneralParameter size(List<GeneralParameter> params) {
		return new GeneralParameter("" + params.size(),
				ParameterType.INTEGER, "size of vector expression",
				params.get(0).getVisitor());
	}

	public GeneralParameter sum(List<GeneralParameter> params)
			throws InvalidExpression {
		boolean allParamsAreNumerical = allParamsInListAreNumerical(params);
		if (!allParamsAreNumerical) {
			throw new InvalidExpression(
					"Cannot compute sum of non numerical type vector expression");
		} else {
			Double sum = 0.0;
			for (int i = 0; i < params.size(); i++) {
				sum = sum + Double.parseDouble(params.get(i).getValue());
			}
			return new GeneralParameter(sum.toString(),
					ParameterType.REAL, "sum of vector expression",
					params.get(0).getVisitor());
		}
	}

	public GeneralParameter product(List<GeneralParameter> params)
			throws InvalidExpression {
		boolean allParamsAreNumerical = allParamsInListAreNumerical(params);
		if (!allParamsAreNumerical) {
			throw new InvalidExpression(
					"Cannot compute product of non numerical type vector expression");
		} else {
			Double product = 0.0;
			for (int i = 0; i < params.size(); i++) {
				product = product
						* Double.parseDouble(params.get(i).getValue());
			}
			return new GeneralParameter(product.toString(),
					ParameterType.REAL,
					"product of vector expression", params.get(0).getVisitor());
		}
	}

	public boolean areGeneralParamtersEqual(GeneralParameter a,
			GeneralParameter b) {

		// If both a and b are numerical
		if (GeneralParameterAlgebra.getInstance()
				.isNumericalGeneralParameter(a)
				&& GeneralParameterAlgebra.getInstance()
						.isNumericalGeneralParameter(b)) {
			double aValue = Double.parseDouble(a.getValue());
			double bValue = Double.parseDouble(b.getValue());
			// The two parameter are equals in the sense defined by epsilon
			return (Math.abs(aValue - bValue) < GeneralParameterAlgebra.epsilon);
		}
		// If the params are not numerical, then they are equal iff both value
		// and type are equals
		return (a.getValue().equals(b.getValue()) && a.getType()
				 == b.getType());
	}

	public boolean isFirstGreaterThanSecond(GeneralParameter a,
			GeneralParameter b, boolean reached) throws InvalidCondition {
		if (GeneralParameterAlgebra.getInstance()
				.isNumericalGeneralParameter(a)
				&& GeneralParameterAlgebra.getInstance()
						.isNumericalGeneralParameter(b)) {
			double aValue = Double.parseDouble(a.getValue());
			double bValue = Double.parseDouble(b.getValue());
			if (reached) {
				return (aValue >= bValue);
			} else {
				return (aValue > bValue);
			}
		}
		throw new InvalidCondition(
				"Cannot eval inequalities over non numerical types");
	}

	public boolean isFirstSmallerThanSecond(GeneralParameter a,
			GeneralParameter b, boolean reached) throws InvalidCondition {
		return GeneralParameterAlgebra.getInstance().isFirstGreaterThanSecond(
				b, a, reached);
	}

	public boolean isGeneralParameterInteger(GeneralParameter a) {
		if (GeneralParameterAlgebra.getInstance()
				.isNumericalGeneralParameter(a)) {
			Double aDoubleValue = Double.parseDouble(a.getValue());
			Integer aIntegerValue = Integer.parseInt(a.getValue());
			return (Math.abs(aDoubleValue - aIntegerValue) < GeneralParameterAlgebra.epsilon);
		} else {
			return false;
		}
	}

	public boolean isGeneralParameterReal(GeneralParameter a) {
		return GeneralParameterAlgebra.getInstance()
				.isNumericalGeneralParameter(a);
	}

	private boolean allParamsInListAreNumerical(List<GeneralParameter> params) {
		boolean allParamsAreNumerical = true;
		for (int i = 0; i < params.size(); i++) {
			allParamsAreNumerical = allParamsAreNumerical
					&& GeneralParameterAlgebra.getInstance()
							.isNumericalGeneralParameter(params.get(i));
		}
		return allParamsAreNumerical;
	}

	private boolean isNumericalGeneralParameter(GeneralParameter param) {
		return (param.getType() == ParameterType.INTEGER || param.getType()
				 == ParameterType.REAL);
	}

	public static void main(String[] args) throws InvalidParameterException,
			InvalidExpression, InvalidCondition {
		Ivisitor visitor = new GeneralParameterVisitor();
		GeneralParameter a = new GeneralParameter("3",
				ParameterType.INTEGER, "a", visitor);
		GeneralParameter b = new GeneralParameter("4",
				ParameterType.INTEGER, "b", visitor);

		GeneralParameter c = GeneralParameterAlgebra.getInstance()
				.substraction(a, b);
		GeneralParameter d = GeneralParameterAlgebra.getInstance().sum(a, b);
		GeneralParameter e = GeneralParameterAlgebra.getInstance().division(a,
				b);
		GeneralParameter f = GeneralParameterAlgebra.getInstance()
				.multiplication(a, b);
		GeneralParameter g = GeneralParameterAlgebra.getInstance().power(a, b);

		List<GeneralParameter> liste = new ArrayList<GeneralParameter>();
		liste.add(a);
		liste.add(b);
		liste.add(c);
		GeneralParameter z = GeneralParameterAlgebra.getInstance().sum(liste);

		GeneralParameter w = GeneralParameterAlgebra.getInstance().sinus(a);

		System.out.println(GeneralParameterAlgebra.getInstance()
				.isFirstSmallerThanSecond(a, a, false));
	}

}
