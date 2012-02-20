package test;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import net.ivoa.parameter.model.Service;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;
import net.ivoa.pdl.interpreter.groupInterpreter.GroupProcessor;

public class IntelligentGUI implements TreeSelectionListener {

	public IntelligentGUI(Service service) {
		super();
		this.gp = new GroupProcessor(service);
		this.gp.process();
		this.serviceName = service.getServiceName();
		this.handlerList = this.gp.getGroupsHandler();

	}

	
	private GroupProcessor gp;
	private String serviceName;
	private List<GroupHandlerHelper> handlerList;
	private PDLTree groupTree;
	private GroupPanel groupPanel;

	private String activeNodeName;

	private JFrame frame;
	private JFrame frameGR;

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		// Create and set up the window.
		this.frame = new JFrame(this.serviceName);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.

		this.groupTree = new PDLTree(this.handlerList, this);
		this.groupPanel = new GroupPanel();

		this.frameGR = new JFrame("Group detail");

		this.frameGR.add(this.groupPanel);
		this.frameGR.setVisible(true);

		frame.add(this.groupTree);
		frame.setSize(250, 300);

		frameGR.setVisible(true);
		frameGR.setSize(450, 450);

		// Display the window.

		frame.setVisible(true);
	}

	/** Required by TreeSelectionListener interface. */
	public void valueChanged(TreeSelectionEvent e) {
		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
				.getNewLeadSelectionPath().getLastPathComponent();
		
		this.activeNodeName = node.toString();
		
		node = this.groupTree.getNodeFromName(this.activeNodeName);

		GroupHandlerHelper helper = (GroupHandlerHelper) node.getUserObject();

		this.groupPanel.updateGroupPanel(helper);
		this.groupPanel.repaint();

	}

}
