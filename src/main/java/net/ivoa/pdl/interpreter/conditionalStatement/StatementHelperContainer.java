package net.ivoa.pdl.interpreter.conditionalStatement;

import net.ivoa.parameter.model.ConditionalStatement;

public class StatementHelperContainer {
    
    
	/**
         * 
         */
        public StatementHelperContainer(ConditionalStatement stmt) {
            this.statement = stmt;
            this.statementSwitched = false;
            this.statementValid = false;
        }
	public ConditionalStatement getStatement() {
		return statement;
	}
	public String getStatementComment() {
		return statement.getComment();
	}
	public Boolean isStatementSwitched() {
		return statementSwitched;
	}
	public Boolean isStatementValid() {
		return statementValid;
	}
	public void setStatementSwitched(Boolean statementSwitched) {
		this.statementSwitched = statementSwitched;
	}
	public void setStatementValid(Boolean statementValid) {
		this.statementValid = statementValid;
	}
	private Boolean statementSwitched;
	private Boolean statementValid;
	private final ConditionalStatement statement;
}
