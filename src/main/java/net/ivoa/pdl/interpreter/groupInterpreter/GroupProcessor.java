package net.ivoa.pdl.interpreter.groupInterpreter;

import java.util.ArrayList;
import java.util.List;

import CommonsObjects.GeneralParameter;

import exeptions.InvalidCondition;
import exeptions.InvalidConditionalStatement;
import exeptions.InvalidCriterion;
import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;
import exeptions.PDLException;

import net.ivoa.gui.dynamicLabel.PDLChoseBoxParamPanel;
import net.ivoa.parameter.model.AbstractCriterion;
import net.ivoa.parameter.model.BelongToSet;
import net.ivoa.parameter.model.DefaultValue;
import net.ivoa.parameter.model.Expression;
import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.Service;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreter;
import net.ivoa.pdl.interpreter.conditionalStatement.ConditionalStatementInterpreterFactory;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class GroupProcessor {

    public GroupProcessor(Service service) {
        super();
        this.service = service;
        ghh = new GroupHandlerHelper(service.getInputs(), null);
        initialize();
    }

    private Service service;
    private List<GroupHandlerHelper> groupsHandler;
    private GroupHandlerHelper ghh;

    public List<GroupHandlerHelper> getGroupsHandler() {
        return groupsHandler;
    }

    public void process() {

        this.groupsHandler = GroupHandlerHelper.getAllgroups();
        this.processStatementsOfGroups();
    }

    private void initialize() {
        // first look through the parameters for conditions

        for (SingleParameter par : service.getParameters().getParameter()) {
            if (par.getRestriction() != null) {
                
                setParamFromCondition(par, par.getRestriction().getAlways().getCriterion());
 
            }
        }
    }

    private void setParamFromCondition(SingleParameter parameter,
            AbstractCriterion criterion) {
        // If the condition is a Default Value
        if (criterion.getConditionType().getClass() == DefaultValue.class) {
            DefaultValue df = (DefaultValue) criterion.getConditionType();

            try {
                // put into the mapper the default value
                List<GeneralParameter> existingValues = Utilities.getInstance()
                        .getMapper()
                        .getuserProvidedValuesForParameter(parameter);

                if (null == existingValues || existingValues.size() < 1) {
                    Utilities
                            .getInstance()
                            .getMapper()
                            .getMap()
                            .put(parameter.getName(),
                                    ExpressionParserFactory.getInstance()
                                            .buildParser(df.getValue()).parse());
                }

            } catch (Exception e) {
                e.printStackTrace();
                // If the default value could not be evaluated, we do
                // nothing

            }
        }

        // if the condition is a Belong To Set
        if (criterion.getConditionType().getClass() == BelongToSet.class) {
            BelongToSet bts = (BelongToSet) criterion.getConditionType();

            List<List<GeneralParameter>> setOfValues = new ArrayList<List<GeneralParameter>>();

            try {
                for (Expression exp : bts.getValue()) {
                    setOfValues.add(ExpressionParserFactory.getInstance()
                            .buildParser(exp).parse());
                }
                List<GeneralParameter> existingValues = Utilities.getInstance()
                        .getMapper()
                        .getuserProvidedValuesForParameter(parameter);

                if (null == existingValues || existingValues.size() < 1) {

                    Utilities.getInstance().getMapper().getMap()
                            .put(parameter.getName(), setOfValues.get(0));
                }

            } catch (Exception e) {
                e.printStackTrace();
                // If the default value could not be evaluated, we do
                // nothing
            }
        }

    }

    private void processStatementsOfGroups() {
        // If the group list is null or void we do nothing
        if (null != this.groupsHandler && this.groupsHandler.size() >= 1) {
            // For each group of the service
            for (GroupHandlerHelper currentGroupHandler : this.groupsHandler) {

                ParameterGroup currentGroup = currentGroupHandler.getGroup();

                if (currentGroup.getActive() != null) {
                    try {
                        ConditionalStatementInterpreter statementInterpreter = ConditionalStatementInterpreterFactory
                                .getInstance().buildInterpreter(
                                        currentGroup.getActive());
                        if (statementInterpreter.isStatementSwitched()) {
                            currentGroupHandler.setGroupActive(true);
                        } else {
                            currentGroupHandler.setGroupActive(false);
                            continue; // do do more testing
                        }
                    } catch (PDLException e) {
                        // just carry on
                        e.printStackTrace();
                    }
                }

                // Handling the case where there is no constraint on a group
                if (null == currentGroup.getConstraintOnGroup()) {
                    // There is no constraint on the group, than it is valid
                    currentGroupHandler.setGroupValid(true);
                    continue;
                }
                // In the case where there is a constraint on group...

                // Building the list of statement of the current group
                List<StatementHelperContainer> statementsHelper = currentGroupHandler
                        .getStatementHelperList();

                // If there is no condition on the group, it is always valid
                if (null == statementsHelper || statementsHelper.size() < 1) {
                    currentGroupHandler.setGroupValid(true);
                } else {
                    // Start initializing the list of statement container to
                    // include into the current groupHelper
                    for (StatementHelperContainer statementHelper : statementsHelper) {
                        try {
                            ConditionalStatementInterpreter statementInterpreter = ConditionalStatementInterpreterFactory
                                    .getInstance().buildInterpreter(
                                            statementHelper.getStatement());

                            statementHelper
                                    .setStatementSwitched(statementInterpreter
                                            .isStatementSwitched());
                            try {
                                statementHelper
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
                                statementHelper.setStatementValid(null);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out
                                    .println("Impossible to verify the statement "
                                            + statementHelper
                                                    .getStatementComment());
                        }
                    }
                }
            }
        }

    }

    /**
     * @return the ghh
     */
    public GroupHandlerHelper getGhh() {
        return ghh;
    }

}
