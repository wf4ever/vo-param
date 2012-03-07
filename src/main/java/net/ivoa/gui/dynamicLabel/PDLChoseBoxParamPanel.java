package net.ivoa.gui.dynamicLabel;

import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import CommonsObjects.GeneralParameter;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class PDLChoseBoxParamPanel extends PDLBaseParamPanel {

	private List<List<GeneralParameter>> setOfValues;

	private JComboBox comboBox;

	public PDLChoseBoxParamPanel(SingleParameter parameter,
			List<List<GeneralParameter>> setOfValues) {
		super(parameter);
		this.setOfValues = setOfValues;
		this.comboBox = new JComboBox();
		initializeComponent();
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void focusLost(FocusEvent arg0) {
		verify();
	}

	@Override
	protected JComponent getComponent() {
		return this.comboBox;
	}

	@Override
	protected String getUserProvidedValue() {
		return this.comboBox.getSelectedItem().toString();
	}

	@Override
	protected void setComponentValue() {
		String preSelectedValue;
		Integer preSelectedIndex=0;
		
		try {
			 preSelectedValue = convertToStringProvidedValues(Utilities
					.getInstance().getMapper()
					.getUserProvidedValuesForParam(this.paramName));
		} catch (Exception e) {
			preSelectedValue = convertToStringProvidedValues(this.setOfValues
					.get(0));
		}
		
		for (int i = 0; i < this.setOfValues.size(); i++) {
			String temp = convertToStringProvidedValues(this.setOfValues
					.get(i));
			this.comboBox.addItem(temp);
			if(preSelectedValue.equalsIgnoreCase(temp)){
				preSelectedIndex = i;
			}
			
		}
		this.comboBox.setSelectedIndex(preSelectedIndex);
	}
}
