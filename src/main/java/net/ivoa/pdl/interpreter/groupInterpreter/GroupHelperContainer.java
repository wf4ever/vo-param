package net.ivoa.pdl.interpreter.groupInterpreter;

import net.ivoa.parameter.model.ConditionalStatement;

public class GroupHelperContainer {
	
	private ConditionalStatement statement;
	public ConditionalStatement getStatement() {
		return statement;
	}
	public String getStatementComment() {
		return StatementComment;
	}
	public boolean isStatementSwitched() {
		return statementSwitched;
	}
	public boolean isStatementValid() {
		return statementValid;
	}
	public void setStatement(ConditionalStatement statement) {
		this.statement = statement;
	}
	public void setStatementComment(String statementComment) {
		StatementComment = statementComment;
	}
	public void setStatementSwitched(boolean statementSwitched) {
		this.statementSwitched = statementSwitched;
	}
	public void setStatementValid(boolean statementValid) {
		this.statementValid = statementValid;
	}
	private String StatementComment;
	private boolean statementSwitched;
	private boolean statementValid;
}
