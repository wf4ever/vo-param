package net.ivoa.pdl.interpreter.conditionalStatement;

import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.ConditionalStatement;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import exeptions.InvalidConditionalStatement;

public class ConditionalStatementInterpreterFactory {

	private static final ConditionalStatementInterpreterFactory instance = new ConditionalStatementInterpreterFactory();

	public static ConditionalStatementInterpreterFactory getInstance() {
		return instance;
	}

	private ConditionalStatementInterpreterFactory() {
	}

	public ConditionalStatementInterpreter buildInterpreter(
			ConditionalStatement statement) throws InvalidConditionalStatement {
		if (statement.getClass() == IfThenConditionalStatement.class) {
			return new IfThenConditionalStatementInterpreter((IfThenConditionalStatement)statement);
		}
		if (statement.getClass() == AlwaysConditionalStatement.class) {
			return new AlwaysConditionalStatementInterpreter(
					(AlwaysConditionalStatement) statement);
		}
		throw new InvalidConditionalStatement("Statement type non handled "
				+ statement.getClass());
	}

}
