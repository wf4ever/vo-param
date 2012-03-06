package dynamicLabel;

import net.ivoa.parameter.model.AbstractCriterion;
import net.ivoa.parameter.model.AlwaysConditionalStatement;
import net.ivoa.parameter.model.AtomicParameterExpression;
import net.ivoa.parameter.model.DefaultValue;
import net.ivoa.parameter.model.IfThenConditionalStatement;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class PDLParamPanelFactory {
	private static final PDLParamPanelFactory instance = new PDLParamPanelFactory();

	private PDLParamPanelFactory() {
		super();
	}

	public static PDLParamPanelFactory getInstance() {
		return instance;
	}

	public PDLBaseParamPanel paramBuilder(GroupHandlerHelper ghh,
			SingleParameter parameter) {

		for (StatementHelperContainer localHelper : ghh
				.getStatementHelperList()) {
			PDLBaseParamPanel panel = test(localHelper, parameter);
			if (null != panel) {
				return panel;
			}
		}
		return new PDLTextParamPanel(parameter);
	}

	private PDLBaseParamPanel test(StatementHelperContainer statementHelp,
			SingleParameter parameter) {
		AbstractCriterion criterion;
		// if the statement is switched and if the parameter is involved into
		// the statement

		AbstractCriterion c1 = this.getCriterionFromStatement(statementHelp);
		AbstractCriterion c2 = this.getCriterionWhereParamIsInvolved(parameter,
				c1);

		if (statementHelp.isStatementSwitched()
				&& null != (criterion = this.getCriterionWhereParamIsInvolved(
						parameter,
						this.getCriterionFromStatement(statementHelp)))) {
			if (criterion.getConditionType().getClass() == DefaultValue.class) {
				DefaultValue df = (DefaultValue) criterion.getConditionType();

				try {
					// put into the mapper the default value
					Utilities
							.getInstance()
							.getMapper()
							.getMap()
							.put(parameter.getName(),
									ExpressionParserFactory.getInstance()
											.buildParser(df.getValue()).parse());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return new PDLTextParamPanel(parameter);
			}
		}
		return new PDLTextParamPanel(parameter);
	}

	private AbstractCriterion getCriterionFromStatement(
			StatementHelperContainer statementHelp) {
		if (statementHelp.getStatement().getClass() == AlwaysConditionalStatement.class) {
			AlwaysConditionalStatement t = (AlwaysConditionalStatement) statementHelp
					.getStatement();
			return t.getAlways().getCriterion();
		}
		if (statementHelp.getStatement().getClass() == IfThenConditionalStatement.class) {
			IfThenConditionalStatement t = (IfThenConditionalStatement) statementHelp
					.getStatement();
			return t.getThen().getCriterion();
		}
		return null;
	}

	private AbstractCriterion getCriterionWhereParamIsInvolved(
			SingleParameter parameter, AbstractCriterion criterion) {
		if (null == criterion)
			return null;

		AbstractCriterion toReturn = null;

		if (criterion.getExpression() instanceof AtomicParameterExpression) {
			AtomicParameterExpression tempExp = (AtomicParameterExpression) criterion
					.getExpression();
			if (Utilities.getInstance()
					.getParameterFromReference(tempExp.getParameterRef())
					.getName().equalsIgnoreCase(parameter.getName())) {
				return criterion;
			} else if (null != criterion.getLogicalConnector()) {
				return getCriterionWhereParamIsInvolved(parameter, criterion
						.getLogicalConnector().getCriterion());
			}

		} else {
			if (null != criterion.getLogicalConnector()) {
				return getCriterionWhereParamIsInvolved(parameter, criterion
						.getLogicalConnector().getCriterion());
			}
		}
		return toReturn;
	}

}
