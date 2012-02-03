package net.ivoa.pdl.interpreter.conditionalStatement;

import net.ivoa.parameter.model.ConditionalStatement;

public class StatementHelperContainer {
	
	public ConditionalStatement getStatement() {
		return statement;
	}
	public String getStatementComment() {
		return StatementComment;
	}
	public Boolean isStatementSwitched() {
		return statementSwitched;
	}
	public Boolean isStatementValid() {
		return statementValid;
	}
	public void setStatement(ConditionalStatement statement) {
		this.statement = statement;
	}
	public void setStatementComment(String statementComment) {
		StatementComment = statementComment;
	}
	public void setStatementSwitched(Boolean statementSwitched) {
		this.statementSwitched = statementSwitched;
	}
	public void setStatementValid(Boolean statementValid) {
		this.statementValid = statementValid;
	}
	private String StatementComment;
	private Boolean statementSwitched;
	private Boolean statementValid;
	private ConditionalStatement statement;
}
