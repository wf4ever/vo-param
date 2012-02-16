package test;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;

import CommonsObjects.GeneralParameter;

public class PDLParamPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425016803025275974L;

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
			String paramDimension) {
		super();
		this.paramName = paramName;
		this.paramUnit = paramUnit;
		this.paramType = paramType;
		this.paramDimension = paramDimension;

		this.setLayout(new GridLayout(1, 2));
		this.paramValue = new JTextField(10);

		this.paramValue.setText(this.buildParamValue());

		this.paramLabel = new JLabel(this.buildLabelText());
		this.add(paramLabel);
		this.add(this.paramValue);

	}

	private String buildParamValue() {
		String toReturn = "";

		List<GeneralParameter> paramValues = Utilities.getInstance()
				.getuserProvidedValuesForParame(this.paramName);

		for (int i = 0; i < paramValues.size(); i++) {
			toReturn = toReturn + paramValues.get(i).getValue();
			if (i < paramValues.size() - 1) {
				toReturn = toReturn + " ; ";
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

	public GeneralParameter verify() {
		// GeneralParameter gp = new GeneralParameter(this.paramValue.getText(),
		// this.param.getParameterType().name(), this.param.getSkossConcept(),
		// new GeneralParameterVisitor());
		// return gp;
		return null;
	}
}
