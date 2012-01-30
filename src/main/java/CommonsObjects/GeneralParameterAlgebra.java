package CommonsObjects;

import visitors.GeneralParameterVisitor;
import visitors.Ivisitor;
import net.ivoa.parameter.model.ParameterType;
import exeptions.InvalidParameterException;

public class GeneralParameterAlgebra {

	private static final GeneralParameterAlgebra instance = new GeneralParameterAlgebra();

	public static GeneralParameterAlgebra getInstance() {
		return instance;
	}


	private GeneralParameterAlgebra() {
	}

	public GeneralParameter sum(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If a is an integer
		if (a.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
			// If b is an integer too
			if (b.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
				Integer Result = Integer.parseInt(a.getValue())
						+ Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER.toString(),
						"result of sum " + a.getDescription() + "+"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real
			if (b.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
				Double Result = Integer.parseInt(a.getValue())
						+ Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL.toString(),
						"result of sum " + a.getDescription() + "+"
								+ b.getDescription(), a.getVisitor());
			}
		}

		// if b is integer
		if (b.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
			return sum(b, a);
		}

		// if a and b are real
		if (a.getType().equalsIgnoreCase(ParameterType.REAL.toString())
				&& b.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
			Double Result = Double.parseDouble(a.getValue())
					+ Double.parseDouble(b.getValue());
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL.toString(), "result of sum "
							+ a.getDescription() + "+" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "+" + b.getDescription());
	}

	public GeneralParameter multiplication(GeneralParameter a,
			GeneralParameter b) throws InvalidParameterException {
		// If a is an integer
		if (a.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
			// If b is an integer too
			if (b.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
				Integer Result = Integer.parseInt(a.getValue())
						* Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER.toString(),
						"result of product " + a.getDescription() + "*"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real
			if (b.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
				Double Result = Integer.parseInt(a.getValue())
						* Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL.toString(),
						"result of product " + a.getDescription() + "*"
								+ b.getDescription(), a.getVisitor());
			}
		}

		// if b is integer
		if (b.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
			return multiplication(b, a);
		}

		// if a and b are real
		if (a.getType().equalsIgnoreCase(ParameterType.REAL.toString())
				&& b.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
			Double Result = Double.parseDouble(a.getValue())
					* Double.parseDouble(b.getValue());
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL.toString(), "result of product "
							+ a.getDescription() + "*" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "*" + b.getDescription());
	}

	public GeneralParameter substraction(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If a is an integer
		if (a.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
			// If b is an integer too
			if (b.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
				Integer Result = Integer.parseInt(a.getValue())
						- Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER.toString(),
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real
			if (b.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
				Double Result = Integer.parseInt(a.getValue())
						- Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL.toString(),
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}
		}

		// If a is real
		if (a.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
			// If b is an integer
			if (b.getType().equalsIgnoreCase(ParameterType.INTEGER.toString())) {
				Double Result = Double.parseDouble(a.getValue())
						- Integer.parseInt(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.INTEGER.toString(),
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(), a.getVisitor());
			}

			// If b is real too
			if (b.getType().equalsIgnoreCase(ParameterType.REAL.toString())) {
				Double Result = Double.parseDouble(a.getValue())
						- Double.parseDouble(b.getValue());
				return new GeneralParameter(Result.toString(),
						ParameterType.REAL.toString(),
						"result of substraction " + a.getDescription() + "-"
								+ b.getDescription(),a.getVisitor());
			}
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "-" + b.getDescription());
	}

	public GeneralParameter division(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If (a is real or integer) and (b is real or integer)
		if ((a.getType().equalsIgnoreCase(ParameterType.REAL.toString()) || a
				.getType().equalsIgnoreCase(ParameterType.INTEGER.toString()))
				&& (b.getType().equalsIgnoreCase(ParameterType.REAL.toString()) || b
						.getType().equalsIgnoreCase(
								ParameterType.INTEGER.toString()))) {
			Double Result = Double.parseDouble(a.getValue())
					/ Double.parseDouble(b.getValue());
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL.toString(), "result of division "
							+ a.getDescription() + "/" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "/" + b.getDescription());
	}

	public GeneralParameter power(GeneralParameter a, GeneralParameter b)
			throws InvalidParameterException {
		// If (a is real or integer) and (b is real or integer)
		if ((a.getType().equalsIgnoreCase(ParameterType.REAL.toString()) || a
				.getType().equalsIgnoreCase(ParameterType.INTEGER.toString()))
				&& (b.getType().equalsIgnoreCase(ParameterType.REAL.toString()) || b
						.getType().equalsIgnoreCase(
								ParameterType.INTEGER.toString()))) {
			Double Result = Math.pow(Double.parseDouble(a.getValue()), Double.parseDouble(b.getValue()));
			return new GeneralParameter(Result.toString(),
					ParameterType.REAL.toString(), "result of power "
							+ a.getDescription() + "powered by" + b.getDescription(),
					a.getVisitor());
		}
		throw new InvalidParameterException("unhandled operation "
				+ a.getDescription() + "powered by" + b.getDescription());
	}
	
	
	
	
	/*public static void main(String[] args) throws InvalidParameterException {
		Ivisitor visitor = new GeneralParameterVisitor();
		GeneralParameter a = new GeneralParameter("3", ParameterType.INTEGER.toString(), "a",visitor );
		GeneralParameter b = new GeneralParameter("4", ParameterType.INTEGER.toString(), "b",visitor );
		
		GeneralParameter c = GeneralParameterAlgebra.getInstance().substraction(a, b);
		GeneralParameter d = GeneralParameterAlgebra.getInstance().sum(a, b);
		GeneralParameter e = GeneralParameterAlgebra.getInstance().division(a, b);
		GeneralParameter f = GeneralParameterAlgebra.getInstance().multiplication(a, b);
		GeneralParameter g = GeneralParameterAlgebra.getInstance().power(a, b);
		System.out.println("cc");
	}*/

}
