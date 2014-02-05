package net.ivoa.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupProcessor;

public class UnifiedGUI implements TreeSelectionListener, ActionListener {

	public UnifiedGUI(Service service) {
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

	private JFrame mainFrame;

	// private JFrame frame;
	// private JFrame frameGR;
	// private JFrame frameServer;

	private JPanel panel;
	private JPanel panelGR;
	private JPanel panelServer;

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		this.panelGR = new JPanel();
		this.panelServer = new JPanel();

		// Create and set up the window.
		this.panel = new JPanel();
		this.mainFrame = new JFrame("PDL client for "+ serviceName);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.

		this.groupTree = new PDLTree(this.gp.getGhh(), this);
		this.groupPanel = new GroupPanel(this);

		this.serverPanel = new PDLSummaryPanel(this.gp);

		// this.frameGR = new JFrame("Group detail");

		// this.frameServer = new JFrame("Summary and server communication");

		this.panelGR.add(this.groupPanel);

		panel.add(this.groupTree);
		// panel.setSize(100, 100);
		panel.setVisible(true);
		panel.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));

		// panelGR.setSize(600, 400);
		panelGR.setVisible(true);
		panelGR.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
		JScrollPane panelGRScroll = new JScrollPane(panelGR);

		panelServer.add(this.serverPanel);
		// panelServer.setSize(100, 250);
		panelServer.setVisible(true);
		panelServer.setBorder(new javax.swing.border.BevelBorder(
				BevelBorder.RAISED));

		mainFrame.setSize(1000, 600);
		mainFrame.setLayout(new GridLayout(1, 3));
		mainFrame.add(panel);
		mainFrame.add(panelGRScroll);
		mainFrame.add(panelServer);

		// Display the window.

		mainFrame.setVisible(true);
		System.out.println(Thread.currentThread() + " is the current tread");
	}

	/** Required by TreeSelectionListener interface. */
	public void valueChanged(TreeSelectionEvent e) {

		System.out.println(Thread.currentThread() + " is the current tread");

		currentGroupH = (GroupHandlerHelper) e.getNewLeadSelectionPath()
				.getLastPathComponent();
		if (currentGroupH.isGroupActive())
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
		this.panelServer.repaint();

	}

	private void refreshTree() {
		this.groupTree = new PDLTree(this.gp.getGhh(), this);
	}

}
