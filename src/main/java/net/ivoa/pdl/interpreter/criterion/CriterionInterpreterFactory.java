package net.ivoa.pdl.interpreter.criterion;

import net.ivoa.parameter.model.AbstractCriterion;
import net.ivoa.parameter.model.Criterion;
import net.ivoa.parameter.model.ParenthesisCriterion;
import net.ivoa.pdl.interpreter.expression.exceptions.InvalidCriterion;

public class CriterionInterpreterFactory {
	private static final CriterionInterpreterFactory instance = new CriterionInterpreterFactory();

	public static CriterionInterpreterFactory getInstance() {
		return instance;
	}

	private CriterionInterpreterFactory() {
	}

	public AbstractCriterionInterpreter buildCriterrionInterpreter(
			AbstractCriterion criterion) throws InvalidCriterion {
		if (criterion.getClass() == Criterion.class) {
			return new CriterionInterpreter((Criterion) criterion);
		}
		if (criterion.getClass() == ParenthesisCriterion.class) {
			return new ParenthesisCriterionInterpreter(
					(ParenthesisCriterion) criterion);
		}
		throw new InvalidCriterion("Criterion type non handled : "
				+ criterion.getClass().toString());
	}

}