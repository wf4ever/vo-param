package test;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PDLStatementPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8682229021240556714L;
	
	private String comment;

	private Boolean isActivated;
	private Boolean isValid;
	
	private JLabel commentLabel;
	
	public PDLStatementPanel(String comment, Boolean isActivated,
			Boolean isValid) {
		super();
		this.comment = comment;
		this.isActivated = isActivated;
		this.isValid = isValid;
		
		if(null!=isActivated && isActivated){
			this.commentLabel = new JLabel(comment);
			this.add(commentLabel);
			this.setVisible(true);
			this.adjusteColors();
		}
		
	}

	private void adjusteColors() {
		if(null==isValid){
			this.setBackground(Color.yellow);
		}else{
			if(this.isValid){
				this.setBackground(Color.green);
			}else{
				this.setBackground(Color.red);
			}
		}
		
	}
	
}
