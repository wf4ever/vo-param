package net.ivoa.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.ivoa.parameter.model.ParameterGroup;
import net.ivoa.parameter.model.ParameterReference;
import net.ivoa.parameter.model.SingleParameter;

public class ParamGroupPanel extends JPanel{

	public ParamGroupPanel(ParameterGroup paramGroup,
			List<SingleParameter> listAllParameter) {
		super();
		this.singleParamPanel = new ArrayList<ParamJPanel>();
		this.subParamGroupPanelList = new ArrayList<ParamGroupPanel>();

		this.add(new JLabel(paramGroup.getName()), CENTER_ALIGNMENT);
		Box column = Box.createVerticalBox();
		List<SingleParameter> paramInThisGroup = buildListParameterInThisGroup(
				paramGroup, listAllParameter);
		for (SingleParameter param : paramInThisGroup) {
			ParamJPanel tempPanel = new ParamJPanel(param);
			column.add(tempPanel);
			this.singleParamPanel.add(tempPanel);
		}
		this.add(column);
		this.setBackground(new Color((float) Math.random(), (float) Math
				.random(), (float) Math.random()));
		for (ParameterGroup pg : paramGroup.getParameterGroup()) {
			ParamGroupPanel tempGrPanel = new ParamGroupPanel(pg, listAllParameter);
			this.add(tempGrPanel);
			this.subParamGroupPanelList.add(tempGrPanel);
		}

	}

	private List<SingleParameter> buildListParameterInThisGroup(
			ParameterGroup paramGroup, List<SingleParameter> listAllParameter) {
		List<SingleParameter> toReturn = new ArrayList<SingleParameter>();
		for (SingleParameter param : listAllParameter) {
			for (ParameterReference paramRef : paramGroup.getParameterRef()) {
				if (param.getName().equalsIgnoreCase(
						paramRef.getParameterName())) {
					toReturn.add(param);
				}
			}
		}
		return toReturn;
	}

	//private ParameterGroup paramGroup;
	private List<ParamJPanel> singleParamPanel;
	
	private List<ParamGroupPanel> subParamGroupPanelList;

	public List<ParamGroupPanel> getSubParamGroupPanelList() {
		return subParamGroupPanelList;
	}

	public List<ParamJPanel> getSingleParamPanel() {
		return singleParamPanel;
	}

}
