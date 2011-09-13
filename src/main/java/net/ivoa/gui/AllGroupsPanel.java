package net.ivoa.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CommonsObjects.GeneralParameter;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.SingleParameter;

public class AllGroupsPanel extends JPanel implements ActionListener {

	private static final String VALIDATE_CODE = "validate";

	private List<ParamGroupPanel> listeParameterGroupsPanel;
	private JButton validateButton;

	public AllGroupsPanel(List<ParameterGroup> listGroupParam,
			List<SingleParameter> allParameter) {
		super();
		this.validateButton = new JButton(VALIDATE_CODE);
		this.validateButton.addActionListener(this);
		this.listeParameterGroupsPanel = new ArrayList<ParamGroupPanel>();
		this.setLayout(new FlowLayout());
		for (ParameterGroup paramGroup : listGroupParam) {
			ParamGroupPanel tempPanel = new ParamGroupPanel(paramGroup,
					allParameter);
			this.add(tempPanel);
			this.listeParameterGroupsPanel.add(tempPanel);
		}
		this.add(validateButton);
	}

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("validate!!");
		validate(this.listeParameterGroupsPanel);
		this.validate();
	}

	private List<GeneralParameter> validate(
			List<ParamGroupPanel> listeParameterGroupsPanel) {
		List<GeneralParameter> toReturn = new ArrayList<GeneralParameter>();
		String alertMessage = "";

		for (ParamGroupPanel groupPanel : listeParameterGroupsPanel) {
			toReturn.addAll(validate(groupPanel.getSubParamGroupPanelList()));
			for (ParamJPanel paramPanel : groupPanel.getSingleParamPanel()) {
				try {
					toReturn.add(paramPanel.verify());
				} catch (Exception e) {
					alertMessage += "Incorrect value for "
							+ paramPanel.getParamName().getText() + "\n";
				}
			}
		}
		if (!alertMessage.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, alertMessage);
		}
		return toReturn;
	}

}
