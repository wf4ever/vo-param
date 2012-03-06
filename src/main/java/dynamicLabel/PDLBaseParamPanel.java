package dynamicLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import visitors.GeneralParameterVisitor;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.utilities.Utilities;

import CommonsObjects.GeneralParameter;

public abstract class PDLBaseParamPanel extends JPanel implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425016803025275974L;
	private static final String SEPARATOR = ";";
	

	public JLabel getParamName() {
		return paramLabel;
	}
	
	protected abstract JComponent getComponent();
	
	public PDLBaseParamPanel(SingleParameter parameter) {
		super();
		this.paramName = parameter.getName();
		this.paramUnit = parameter.getUnit();
		this.paramType = parameter.getParameterType().toString();
		this.skossConcept = parameter.getSkossConcept();
		this.paramDimension = null;
		try {
			this.paramDimension = ExpressionParserFactory.getInstance()
					.buildParser(parameter.getDimension()).parse()
					.get(0).getValue();
		} catch (Exception e) {
			// do nothing and dimension is null
		}
		this.setLayout(new GridLayout(1, 2));
		
		this.paramLabel = new JLabel(this.buildLabelText());
		

	}

	protected void initializeComponent() {
		this.getComponent().setToolTipText(this.skossConcept);
		this.add(paramLabel);
		this.add(this.getComponent());
		this.setComponentValue();
		this.getComponent().addFocusListener(this);
		
	}

	protected abstract String getUserProvidedValue();

	
	protected abstract void setComponentValue();
	
	
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
	protected String paramName;
	private String paramUnit;
	private String paramType;
	private String paramDimension;
	private String skossConcept;

	public void verify() {
		String userProvidedString = getUserProvidedValue();

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
}
