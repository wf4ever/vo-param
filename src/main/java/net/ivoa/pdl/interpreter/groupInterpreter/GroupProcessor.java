package net.ivoa.pdl.interpreter.groupInterpreter;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.ConditionalStatement;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreter;
import net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreterFactory;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;

public class GroupProcessor {

	public GroupProcessor(Service service) {
		super();
		this.service = service;
	}

	private Service service;
	private List<GroupHandlerHelper> groupsHandler;

	public List<GroupHandlerHelper> getGroupsHandler() {
		return groupsHandler;
	}

	public void process() {
		this.groupsHandler = buildGroupListFromService();
		this.processStatementsOfGroups();
	}

	private void processStatementsOfGroups() {
		// If the group list is null or void we do nothing
		if (null != this.groupsHandler && this.groupsHandler.size() >= 1) {
			// For each group of the service
			for (int i = 0; i < this.groupsHandler.size(); i++) {

				ParameterGroup currentGroup = this.groupsHandler.get(i)
						.getGroup();

				// Handling the case where there is no constraint on a group
				if (null == currentGroup.getConstraintOnGroup()) {
					return;
				}
				// In the case where there is a constraint on group...
				
				// Building the list of statement of the current group
				List<ConditionalStatement> statementList = currentGroup
						.getConstraintOnGroup().getConditionalStatement();

				// If there is no condition on the group, it is always valid
				if (null == statementList || statementList.size() < 1) {
					this.groupsHandler.get(i).setGroupValid(true);
				} else {
					// Start initializing the list of statement container to
					// include into the current groupHelper
					List<StatementHelperContainer> statementsHelper = new ArrayList<StatementHelperContainer>();
					for (ConditionalStatement statement : statementList) {
						try {
							ConditionalStatementInterpreter statementInterpreter = ConditionalStatementInterpreterFactory
									.getInstance().buildInterpreter(statement);

							StatementHelperContainer tempHelper = new StatementHelperContainer();
							tempHelper.setStatement(statement);
							tempHelper.setStatementComment(statement
									.getComment());
							tempHelper
									.setStatementSwitched(statementInterpreter
											.isStatementSwitched());
							try {
								tempHelper
										.setStatementValid(statementInterpreter
												.isValidStatement());
							} catch (Exception e) {
								// If one cannot evaluate the condition, this
								// means
								// that one cannot say nothing about the
								// validity
								// of the statement
								e.printStackTrace();
								System.out
										.println("Indertminate statement validity");
								tempHelper.setStatementValid(null);
							}
							statementsHelper.add(tempHelper);

						} catch (Exception e) {
							e.printStackTrace();
							System.out
									.println("Impossible to verify the statement "
											+ statement.getComment());
						}
					}
					this.groupsHandler.get(i).setStatementHelperList(
							statementsHelper);
				}
			}
		}

	}

	private List<GroupHandlerHelper> buildGroupListFromService() {
		List<GroupHandlerHelper> toReturn = new ArrayList<GroupHandlerHelper>();
		addGroups(toReturn, service.getInputs(), "root");
		return toReturn;
	}

	private void addGroups(List<GroupHandlerHelper> toReturn,
			ParameterGroup input, String fatherName) {
		GroupHandlerHelper temp = new GroupHandlerHelper();
		temp.setGroup(input);
		temp.setFatherName(fatherName);

		if (null == input.getParameterGroup()
				|| input.getParameterGroup().size() < 1) {
			temp.setSonNames(null);
			toReturn.add(temp);
		} else {
			List<String> sonNames = new ArrayList<String>();
			List<ParameterGroup> sons = input.getParameterGroup();
			for (int i = 0; i < sons.size(); i++) {
				sonNames.add(sons.get(i).getName());
			}
			temp.setSonNames(sonNames);
			toReturn.add(temp);
			for (int i = 0; i < sons.size(); i++) {
				addGroups(toReturn, sons.get(i), input.getName());
			}
		}

	}

}
