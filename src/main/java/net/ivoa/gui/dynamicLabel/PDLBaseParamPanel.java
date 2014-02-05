package net.ivoa.gui.dynamicLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.ivoa.gui.GroupPanel;
import net.ivoa.parameter.model.ParameterType;
import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.utilities.SkossConverter;
import net.ivoa.pdl.interpreter.utilities.Utilities;
import visitors.GeneralParameterVisitor;
import CommonsObjects.GeneralParameter;

public abstract class PDLBaseParamPanel extends JPanel implements FocusListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5425016803025275974L;
	private static final String SEPARATOR = ";";

	private JButton infoButton;
	
	public JLabel getParamName() {
		return paramLabel;
	}

	protected abstract JComponent getComponent();

	public PDLBaseParamPanel(SingleParameter parameter) {
		super();
		System.out.println(Thread.currentThread()+" is the current tread");
		this.paramName = parameter.getName();
		this.paramUnit = parameter.getUnit();
		this.paramType = parameter.getParameterType();
		this.skossConcept = parameter.getSkosConcept();
		this.paramDimension = null;
		try {
			this.paramDimension = ExpressionParserFactory.getInstance()
					.buildParser(parameter.getDimension()).parse().get(0)
					.getValue();
		} catch (Exception e) {
			// do nothing and dimension is null
		}
		this.setLayout(new GridLayout(1, 2));

		this.paramLabel = new JLabel(this.buildLabelText());

	}

	protected void initializeComponent() {
		this.getComponent().setToolTipText(SkossConverter.getInstance().getSkosDescriptionBySkosURI(this.skossConcept));
		this.add(paramLabel);
		this.add(this.getComponent());
		this.setComponentValue();
		
		this.infoButton  = new JButton();
		this.infoButton.setText("?");
		this.infoButton.addActionListener(this);
		
		this.infoButton.setSize(20,20);
		
		this.add(this.infoButton);
		this.getComponent().addFocusListener(this);

	}
	
	public void actionPerformed(ActionEvent e) {
		String infoMessage = SkossConverter.getInstance().getSkosDescriptionBySkosURI(this.skossConcept);
		
		JOptionPane.showMessageDialog(this,infoMessage,"Parameter description for "+this.paramName,JOptionPane.INFORMATION_MESSAGE);
	}

	protected abstract String getUserProvidedValue();

	protected abstract void setComponentValue();

	private String buildLabelText() {
		String toReturn = " "+this.paramName + " (" + this.paramUnit +")";
		return toReturn;
	}

	private JLabel paramLabel;
	protected String paramName;
	private String paramUnit;
	private ParameterType paramType;
	private String paramDimension;
	private String skossConcept;

	public void verify() {
		String userProvidedString = getUserProvidedValue();
		if("".equalsIgnoreCase(userProvidedString) || null==userProvidedString){
			return;
		}

		String[] vectorExpression = userProvidedString.split(SEPARATOR);

		// Performing the verification of size dimension
		try {
			Integer dimension = Integer.parseInt(this.paramDimension);
			if (dimension != vectorExpression.length) {
				JOptionPane.showMessageDialog(this,
						"Invalid vector size, exmpected == " + dimension,
						"Error on parameter " + this.paramName,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (Exception e) {
			// No verification possible since the dimension could not be
			// evaluated
		}

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
		//this.validateProvidedValues();

	}
	
	protected void validateProvidedValues(){
		GroupPanel myFather = (GroupPanel) this.getParent().getParent();
		myFather.clickOnValidate();
	}

	protected String convertToStringProvidedValues(
			List<GeneralParameter> paramValues) {
		String toReturn = "";
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
}
