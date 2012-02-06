package net.ivoa.pdl.interpreter.groupInterpreter;

import java.util.List;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class GroupHandlerHelper {
	
	private String fatherName;
	private List<String> sonNames;
	private String groupName;
	private List<SingleParameter> singleParamsIntoThisGroup;
	private Boolean groupValid;

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
		this.singleParamsIntoThisGroup = Utilities.getInstance().getParameterForTheGroup(this.group);
	}
	public void setStatementHelperList(
			List<StatementHelperContainer> statementHelperList) {
		this.statementHelperList = statementHelperList;
	}
	
	public String getGroupName() {
		return groupName;
	}
	private ParameterGroup group;
	
	public List<SingleParameter> getSingleParamIntoThisGroup() {
		return singleParamsIntoThisGroup;
	}
	private List<StatementHelperContainer> statementHelperList;
	
	

	public Boolean getGroupValid() {
		return groupValid;
	}
	public void setGroupValid(Boolean groupValid) {
		this.groupValid = groupValid;
	}
	
	
}
