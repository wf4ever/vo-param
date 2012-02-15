package test;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;

public class GroupPanel extends JPanel{

	public GroupPanel() {
		super();
		
	}
	
	public void updateGroupPanel(GroupHandlerHelper ghh){
		this.setVisible(false);
		JButton b= new JButton(ghh.getGroupName());
		JScrollPane grPane = new JScrollPane();
		grPane.add(b);
		this.add(grPane);
		this.setVisible(true);
		this.doLayout();
	}
	
}
