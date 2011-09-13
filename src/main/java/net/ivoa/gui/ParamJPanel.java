package net.ivoa.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import visitors.GeneralParameterVisitor;
import visitors.Ivisitor;

import net.ivoa.parameter.model.SingleParameter;
import CommonsObjects.GeneralParameter;

public class ParamJPanel extends JPanel {
	public JLabel getParamName() {
		return paramName;
	}
	public JTextField getParamValue() {
		return paramValue;
	}
	public void setParamName(JLabel paramName) {
		this.paramName = paramName;
	}
	public void setParamValue(JTextField paramValue) {
		this.paramValue = paramValue;
	}
	public ParamJPanel(SingleParameter param) {
		super();
		this.param = param;
		this.setLayout(new GridLayout(1,2));
		
		this.paramValue =  new JTextField(10);
		
		this.paramName = new JLabel(this.param.getName() + " ( " + this.param.getUnit() + "; "
				+ this.param.getParameterType() + ")");
		
		this.add(paramName);
		this.add(this.paramValue);

	}
	
	private JLabel paramName;
	private SingleParameter param;
	private JTextField paramValue;
	
	
	public GeneralParameter verify(){
		GeneralParameter gp = new GeneralParameter(this.paramValue.getText(), this.param.getParameterType().name(), this.param.getSkossConcept(), new GeneralParameterVisitor());
		return gp;
	}

}
