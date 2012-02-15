package test;
import java.util.List;

import javax.swing.JFrame;

import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;


public class IntelligentGUI {
	
	public IntelligentGUI(String serviceName,
			List<GroupHandlerHelper> handlerList) {
		super();
		this.serviceName = serviceName;
		this.handlerList = handlerList;
	}

	private String serviceName;
	private List<GroupHandlerHelper> handlerList;
	
	
	
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public void createAndShowGUI() {
		
		// Create and set up the window.
		JFrame frame = new JFrame(this.serviceName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		
		PDLTree parameterGroupTree = new PDLTree(this.handlerList);
		
		JFrame frameGr = new JFrame("group Frame");
		frameGr.add(parameterGroupTree.getActiveGroupPanel());
		frameGr.setVisible(true);
		
		frame.add(parameterGroupTree);
		frame.setSize(1000, 1000);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}
