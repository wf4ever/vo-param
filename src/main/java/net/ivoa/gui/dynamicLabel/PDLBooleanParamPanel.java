package net.ivoa.gui.dynamicLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import CommonsObjects.GeneralParameter;

import exeptions.InvalidExpression;
import exeptions.InvalidParameterException;

import net.ivoa.parameter.model.SingleParameter;
import net.ivoa.pdl.interpreter.expression.ExpressionParserFactory;
import net.ivoa.pdl.interpreter.utilities.Utilities;

public class PDLBooleanParamPanel extends PDLBaseParamPanel implements ActionListener{

	private List<JCheckBox> boxList;
	private JPanel checkPanel;
	private SingleParameter parameter;

	public PDLBooleanParamPanel(SingleParameter parameter) {
		super(parameter);
		this.checkPanel = new JPanel();
		this.boxList = new ArrayList<JCheckBox>();
		this.parameter = parameter;
		initializeComponent();
		
	}
	
	private List<Boolean> getPreEnteredValues(){
		List<Boolean> toReturn = new ArrayList<Boolean>();
		List<GeneralParameter> paramList = Utilities.getInstance().getuserProvidedValuesForParame(this.paramName);
		for(int i=0; i<paramList.size();i++){
			toReturn.add(paramList.get(i).getValue().equalsIgnoreCase("true"));
		}
		return toReturn;
	}
	

	public void focusGained(FocusEvent arg0) {
	}

	public void focusLost(FocusEvent arg0) {
		verify();
	}

	@Override
	protected JComponent getComponent() {
		return this.checkPanel;
	}

	@Override
	protected String getUserProvidedValue() {
		String toReturn="";
		
		for (int i=0;i<this.boxList.size();i++){
			Boolean temp = new Boolean(this.boxList.get(i).isSelected());
			toReturn = toReturn + temp.toString();
			if(i>0 || i<this.boxList.size()-1){
				toReturn = toReturn+";";
			}
		}
		return toReturn;
	}

	@Override
	protected void setComponentValue() {
		Integer dimension;
		try {
			dimension = Integer.parseInt(ExpressionParserFactory
					.getInstance().buildParser(parameter.getDimension())
					.parse().get(0).getValue());
		} catch (Exception e) {
			dimension =1;
			e.printStackTrace();
		}
		
		List<Boolean> preEnteredValues = getPreEnteredValues();
		
		for(int i=0;i<dimension;i++){
			JCheckBox tempBox = new JCheckBox();
			tempBox.setSelected(preEnteredValues.get(i));
			tempBox.addActionListener(this);
			this.boxList.add(tempBox);
			this.checkPanel.add(tempBox);
		}
	}

	public void actionPerformed(ActionEvent e) {
		verify();
	}
}
