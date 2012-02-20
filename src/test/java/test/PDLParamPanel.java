package test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import visitors.GeneralParameterVisitor;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

import CommonsObjects.GeneralParameter;

public class PDLParamPanel extends JPanel implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425016803025275974L;
	private static final String SEPARATOR = ";";

	public JLabel getParamName() {
		return paramLabel;
	}

	public JTextField getParamValue() {
		return paramValue;
	}

	public void setParamValue(JTextField paramValue) {
		this.paramValue = paramValue;
	}

	public PDLParamPanel(String paramName, String paramUnit, String paramType,
			String paramDimension, String skosConcept) {
		super();
		this.paramName = paramName;
		this.paramUnit = paramUnit;
		this.paramType = paramType;
		this.paramDimension = paramDimension;
		this.skossConcept = skosConcept;

		this.setLayout(new GridLayout(1, 2));
		this.paramValue = new JTextField(10);

		this.paramValue.setText(this.buildParamValue());

		this.paramLabel = new JLabel(this.buildLabelText());
		this.paramValue.setToolTipText(this.skossConcept);
		this.add(paramLabel);
		this.add(this.paramValue);
		this.paramValue.addFocusListener(this);
	}

	private String buildParamValue() {
		String toReturn = "";

		List<GeneralParameter> paramValues = Utilities.getInstance()
				.getuserProvidedValuesForParame(this.paramName);
		if (null != paramValues) {
			for (int i = 0; i < paramValues.size(); i++) {
				if (null != paramValues.get(i)) {
					toReturn = toReturn + paramValues.get(i).getValue();
					if (i < paramValues.size() - 1) {
						toReturn = toReturn + " ; ";
					}
				}
			}
		}

		return toReturn;
	}

	private String buildLabelText() {
		String toReturn = this.paramName + " ( " + this.paramUnit + "; "
				+ this.paramType;

		if (null != paramDimension) {
			toReturn = toReturn + " " + this.paramDimension;
		}
		toReturn = toReturn + ")";
		return toReturn;
	}

	private JLabel paramLabel;
	private JTextField paramValue;
	private String paramName;
	private String paramUnit;
	private String paramType;
	private String paramDimension;
	private String skossConcept;

	public void verify() {
		String userProvidedString = this.paramValue.getText();

		String[] vectorExpression = userProvidedString.split(SEPARATOR);

		// TODO put the verification of size dimension

		List<GeneralParameter> generalParamList = new ArrayList<GeneralParameter>();

		for (int i = 0; i < vectorExpression.length; i++) {
			try {
				GeneralParameter gp = new GeneralParameter(vectorExpression[i],
						this.paramType, this.paramName,
						new GeneralParameterVisitor());
				generalParamList.add(gp);

			} catch (Exception e) {

				JOptionPane.showMessageDialog(this, vectorExpression[i]
						+ " is an incorrect value for " + this.paramName
						+ " (composant " + (i + 1) + ")  of type "
						+ this.paramType, "Error on parameter "
						+ this.paramName, JOptionPane.ERROR_MESSAGE);
			}
			// Updating the parameters with the new provided value
			Utilities.getInstance().getMapper().getMap()
					.put(this.paramName, generalParamList);
		}

	}

	public void focusGained(FocusEvent arg0) {
		System.out.println("focus gained " + this.paramValue.getText());
	}

	public void focusLost(FocusEvent arg0) {
		System.out.println("focus lost " + this.paramValue.getText());
		this.verify();

	}

}
