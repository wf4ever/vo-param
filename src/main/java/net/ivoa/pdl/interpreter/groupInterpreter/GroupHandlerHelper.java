package net.ivoa.pdl.interpreter.groupInterpreter;

import java.util.List;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;

public class GroupHandlerHelper {
	
	

	public GroupHandlerHelper() {
		super();
	}
	public String getFatherName() {
		return fatherName;
	}
	public List<String> getSonNames() {
		return sonNames;
	}
	public ParameterGroup getGroup() {
		return group;
	}
	public List<StatementHelperContainer> getStatementHelperList() {
		return statementHelperList;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public void setSonNames(List<String> sonNames) {
		this.sonNames = sonNames;
	}
	public void setGroup(ParameterGroup group) {
		this.group = group;
		this.groupName = group.getName();
	}
	public void setStatementHelperList(
			List<StatementHelperContainer> statementHelperList) {
		this.statementHelperList = statementHelperList;
	}
	private String fatherName;
	private List<String> sonNames;
	private String groupName;
	private ParameterGroup group;
	private List<StatementHelperContainer> statementHelperList;
	
	private Boolean groupValid;

	public Boolean getGroupValid() {
		return groupValid;
	}
	public void setGroupValid(Boolean groupValid) {
		this.groupValid = groupValid;
	}
	
	
}