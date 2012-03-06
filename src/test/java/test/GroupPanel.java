package test;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import dynamicLabel.PDLBaseParamPanel;
import dynamicLabel.PDLParamPanelFactory;
import dynamicLabel.PDLTextParamPanel;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.conditionalStatement.StatementHelperContainer;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;

public class GroupPanel extends JPanel {

	private JPanel containedPanel;
	private JPanel statementPanel;
	private List<PDLBaseParamPanel> paramsPanels;
	private List<PDLStatementPanel> statementsPanelList;
	private JButton validateButton;

	public GroupPanel(ActionListener listener) {
		super();
		this.validateButton = new JButton("validate");
		this.validateButton.addActionListener(listener);
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

		this.setLayout(new GridLayout());

		this.setVisible(true);
		this.revalidate();

	}

	private void buildStatementPanel(GroupHandlerHelper ghh) {
		this.statementPanel = new JPanel();
		List<StatementHelperContainer> shc = ghh.getStatementHelperList();
		if (null != shc) {
			this.statementsPanelList = new ArrayList<PDLStatementPanel>(
					shc.size());
			for (int i = 0; i < shc.size(); i++) {
				String comment = shc.get(i).getStatementComment();
				Boolean isActivated = shc.get(i).isStatementSwitched();
				Boolean isValid = shc.get(i).isStatementValid();
				PDLStatementPanel temp = new PDLStatementPanel(comment,
						isActivated, isValid);
				this.statementsPanelList.add(temp);
				temp.setVisible(true);
				this.statementPanel.add(temp);
			}
			this.statementPanel.add(this.validateButton);

			this.statementPanel.setLayout(new GridLayout(shc.size() + 3, 1));
		}

	}

	private void addParamsToContainedPanel() {
		for (PDLBaseParamPanel currentParamPanel : this.paramsPanels) {
			this.containedPanel.add(currentParamPanel);
			currentParamPanel.setVisible(true);
		}
	}

	private void buildListPanel(GroupHandlerHelper ghh) {
		List<SingleParameter> paramsList = ghh.getSingleParamIntoThisGroup();
		this.paramsPanels = new ArrayList<PDLBaseParamPanel>(paramsList.size());

		for (int i = 0; i < paramsList.size(); i++) {
			this.paramsPanels.add(PDLParamPanelFactory.getInstance()
					.paramBuilder(ghh, paramsList.get(i)));
		}

	}

	private GridLayout computeGoodLayout() {
		return new GridLayout(this.paramsPanels.size(), 1);
	}

}
