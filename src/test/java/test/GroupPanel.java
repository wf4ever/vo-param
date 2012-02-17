package test;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;

public class GroupPanel extends JPanel {

	private JPanel containedPanel;
	private JPanel statementPanel;
	private List<PDLParamPanel> paramsPanels;
	private List<PDLStatementPanel> statementsPanelList;

	public GroupPanel() {
		super(new FlowLayout());
	}

	public void updateGroupPanel(GroupHandlerHelper ghh) {

		if (null != this.containedPanel) {
			this.remove(this.containedPanel);
		}

		this.containedPanel = new JPanel();

		// build the list of panel for each parameter in this group
		this.buildListPanel(ghh);
		this.setLayout(this.computeGoodLayout());
		// Adding the params panel to the containedPanel
		addParamsToContainedPanel();

		buildStatementPanel(ghh);
		
		this.containedPanel.add(this.statementPanel);

		this.containedPanel.setVisible(true);

		this.add(this.containedPanel);

		this.setVisible(true);
		this.revalidate();

	}

	private void buildStatementPanel(GroupHandlerHelper ghh) {
		this.statementPanel = new JPanel();
		List<StatementHelperContainer> shc = ghh.getStatementHelperList();
		this.statementsPanelList = new ArrayList<PDLStatementPanel>(
				shc.size());
		for(int i=0;i<shc.size();i++){
			String comment = shc.get(i).getStatementComment();
			Boolean isActivated = shc.get(i).isStatementSwitched();
			Boolean isValid = shc.get(i).isStatementValid();
			PDLStatementPanel temp = new PDLStatementPanel(comment, isActivated, isValid);
			this.statementsPanelList.add(temp);
			temp.setVisible(true);
			this.statementPanel.add(temp);
		}
		this.statementPanel.setLayout(new GridLayout(shc.size(),1));
		
		
	}

	private void addParamsToContainedPanel() {
		for (PDLParamPanel currentParamPanel : this.paramsPanels) {
			this.containedPanel.add(currentParamPanel);
			currentParamPanel.setVisible(true);
		}
	}

	private void buildListPanel(GroupHandlerHelper ghh) {
		List<SingleParameter> paramsList = ghh.getSingleParamIntoThisGroup();
		this.paramsPanels = new ArrayList<PDLParamPanel>(paramsList.size());

		for (int i = 0; i < paramsList.size(); i++) {
			String paramName = paramsList.get(i).getName();
			String paramUnit = paramsList.get(i).getUnit();
			String paramType = paramsList.get(i).getParameterType().toString();
			String skosConcept = paramsList.get(i).getSkossConcept();
			String paramDimension = null;
			this.paramsPanels.add(new PDLParamPanel(paramName, paramUnit,
					paramType, paramDimension,skosConcept));
		}

	}

	private GridLayout computeGoodLayout() {
		return new GridLayout(this.paramsPanels.size(), 1);
	}

}
