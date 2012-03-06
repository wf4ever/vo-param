package dynamicLabel;

import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextField;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.utilities.Utilities;
import CommonsObjects.GeneralParameter;

public class PDLTextParamPanel extends PDLBaseParamPanel {
	
	private JTextField textField;
	
	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public PDLTextParamPanel(SingleParameter parameter) {
		super(parameter);
		this.textField = new JTextField();
		initializeComponent();
	}

	private JTextField paramValue;

	public JTextField getParamValue() {
		return paramValue;
	}

	public void setParamValue(JTextField paramValue) {
		this.paramValue = paramValue;
	}

	public void focusGained(FocusEvent arg0) {
		
	}

	public void focusLost(FocusEvent arg0) {
		verify();
	}

	@Override
	protected JComponent getComponent() {
		return this.textField;
	}

	@Override
	protected String getUserProvidedValue() {
		return this.textField.getText();
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

	@Override
	protected void setComponentValue() {
		this.textField.setText(this.buildParamValue());
	}

	

}
