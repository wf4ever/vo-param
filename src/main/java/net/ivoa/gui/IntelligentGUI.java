package net.ivoa.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupProcessor;

public class IntelligentGUI implements TreeSelectionListener, ActionListener {

	public IntelligentGUI(Service service) {
		super();
		this.gp = new GroupProcessor(service);
		this.gp.process();
		this.serviceName = service.getServiceName();
	}

	private GroupProcessor gp;
	private String serviceName;
	private PDLTree groupTree;
	private GroupPanel groupPanel;
	private PDLSummaryPanel serverPanel;
	
	private GroupHandlerHelper currentGroupH;

	private JFrame frame;
	private JFrame frameGR;
	private JFrame frameServer;

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		// Create and set up the window.
		this.frame = new JFrame(this.serviceName);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.

		this.groupTree = new PDLTree(this.gp.getGhh(), this);
		this.groupPanel = new GroupPanel(this);
		
		this.serverPanel = new PDLSummaryPanel(this.gp);
		
		this.frameGR = new JFrame("Group detail");
		
		this.frameServer = new JFrame("Summary and server communication");
		
		this.frameGR.add(this.groupPanel);
		
		
		frame.add(this.groupTree);
		frame.setSize(250, 300);
		frame.setLocation(145, 0);

		frameGR.setVisible(true);
		frameGR.setSize(600, 600);
		frameGR.setLocation(400,0);
		
		frameServer.add(this.serverPanel);
		frameServer.setSize(350, 350);
		frameServer.setVisible(true);
		frameServer.setLocation(40, 330);

		// Display the window.

		frame.setVisible(true);
		System.out.println(Thread.currentThread()+" is the current tread");
	}

	/** Required by TreeSelectionListener interface. */
	public void valueChanged(TreeSelectionEvent e) {
		
		System.out.println(Thread.currentThread()+" is the current tread");
		
		currentGroupH = (GroupHandlerHelper) e
				.getNewLeadSelectionPath().getLastPathComponent();
		if(currentGroupH.isGroupActive())
		    this.updateGroupPanel();

	}

	private void updateGroupPanel() {
		
		this.groupPanel.updateGroupPanel(currentGroupH);
		this.groupPanel.setSize(450, 450);
		this.groupPanel.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("VALIDATION");
		this.gp.process();
		this.refreshTree();
		this.updateGroupPanel();
		
		this.serverPanel.updateSummary();
		this.serverPanel.revalidate();
		this.frameServer.repaint();
		
		
	}

	private void refreshTree() {
		this.groupTree = new PDLTree(this.gp.getGhh(), this);
	}

}
