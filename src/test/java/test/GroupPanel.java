package test;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;



public class GroupPanel extends JPanel{
	
	private JPanel containedPanel;
	
	public GroupPanel() {
		super(new FlowLayout());
	}
	
	public void updateGroupPanel(GroupHandlerHelper ghh){
		
		if(null!=this.containedPanel){
			this.remove(this.containedPanel);
		}
		
		this.containedPanel= new JPanel(new FlowLayout());
		
		// defining the new button
		JButton b= new JButton(ghh.getGroupName());
		
		this.containedPanel.add(b);
		b.setVisible(true);
		
		this.containedPanel.setVisible(true);
		this.containedPanel.repaint();
		
		this.add(this.containedPanel);
		
		this.setVisible(true);
		this.repaint();
		
		
		//this.doLayout();
		
		
		System.out.println("Creation bouton "+ghh.getGroupName());
	}
	
}
