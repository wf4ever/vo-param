package net.ivoa.pdl.interpreter.groupInterpreter;

import java.util.ArrayList;
import java.util.List;

import net.ivoa.parameter.model.ConditionalStatement;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreter;
import net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreterFactory;
import exeptions.InvalidCondition;
import exeptions.InvalidConditionalStatement;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

public class GroupInterpreter {

	public GroupInterpreter(ParameterGroup paramGroup) {
		super();
		this.paramGroup = paramGroup;
	}

	private ParameterGroup paramGroup;
	
	private List<GroupHelperContainer> groupSummary;

	public List<GroupHelperContainer> getGroupSummary() {
		return groupSummary;
	}

	public boolean verifyGroup() throws InvalidConditionalStatement,
			InvalidExpression, InvalidParameterException, InvalidCondition,
			InvalidCriterion {
		List<ConditionalStatement> statementList = this.paramGroup
				.getConstraintOnGroup().getConditionalStatement();
		if (null == statementList || statementList.size() < 1) {
			// If there is no condition on the group, it is always valid
			return true;
		} else {
			List<GroupHelperContainer> groupHelper = new ArrayList<GroupHelperContainer>();
			boolean toReturn = true;
			for (ConditionalStatement statement : statementList) {
				ConditionalStatementInterpreter statementInterpreter = ConditionalStatementInterpreterFactory
						.getInstance().buildInterpreter(statement);

				GroupHelperContainer tempHelper = new GroupHelperContainer();
				tempHelper.setStatement(statement);
				tempHelper.setStatementSwitched(statementInterpreter
						.isStatementSwitched());
				tempHelper.setStatementValid(statementInterpreter
						.isValidStatement());
				groupHelper.add(tempHelper);
				
				toReturn = toReturn && tempHelper.isStatementValid();

			}
			this.groupSummary = groupHelper;
			return toReturn;
		}
	}

}
