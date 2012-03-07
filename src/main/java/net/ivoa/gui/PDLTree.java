package net.ivoa.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;

public class PDLTree extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private List<DefaultMutableTreeNode> nodeList;
	
	public PDLTree(List<GroupHandlerHelper> groupsHandler,TreeSelectionListener treeListener) {
		
		
		super(new GridLayout(1, 0));
		
		this.updateTree(groupsHandler);
		
		// Listen for when the selection changes.
		tree.addTreeSelectionListener(treeListener);
		
		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree);

		Dimension minimumSize = new Dimension(300, 300);
		treeView.setMinimumSize(minimumSize);

		// Add the split pane to this panel.
		add(treeView);
		
	}
	
	public DefaultMutableTreeNode getNodeFromName(String nodeName){
		for(DefaultMutableTreeNode currentNode : this.nodeList){
			if(currentNode.toString().equals(nodeName)){
				return currentNode;
			}
		}
		return null;
	}
	
	public void updateTree(List<GroupHandlerHelper> groupsHandler){
		this.nodeList = new ArrayList<DefaultMutableTreeNode>();

		// Create the nodes.
		DefaultMutableTreeNode root = createNodes(groupsHandler);

		// Create a tree that allows one selection at a time.
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	

	private DefaultMutableTreeNode createNodes(
			List<GroupHandlerHelper> groupsHandler) {

		// List of helper to convert into nodes
		List<GroupHandlerHelper> toConvertIntoNodes = new ArrayList<GroupHandlerHelper>();

		toConvertIntoNodes.addAll(groupsHandler);

		// We build the root node
		GroupHandlerHelper rootHelper = findRootElement(groupsHandler);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootHelper);

		// we add it to the created node list
		this.nodeList.add(root);

		// we remove the root from the list of nodes to create
		toConvertIntoNodes.remove(rootHelper);

		while (toConvertIntoNodes.size() > 0) {
			for (int i = 0; i < toConvertIntoNodes.size(); i++) {
				// Create the node for the current element of the list
				DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(
						toConvertIntoNodes.get(i));

				// Find the parent node of the just created node
				DefaultMutableTreeNode parentNode = findParentBetweenCreatedNodes(toConvertIntoNodes
						.get(i).getFatherName());
				// If the parent not exist we do nothing
				if (null == parentNode) {
					// We do nothing
				} else {
					// In the opposite case we add the node to the parent
					parentNode.add(currentNode);
					// We add the node just created to the list of existing nodes
					this.nodeList.add(currentNode);
					// We remove the handler related to the placed node from the
					// list of nodes to create
					toConvertIntoNodes.remove(i);
					// Important, since we remove an element, let us
					// de-increment
					// the loop counter
					i--;
				}
			}
		}
		return root;
	}

	private DefaultMutableTreeNode findParentBetweenCreatedNodes(
			String fatherName) {
		for (DefaultMutableTreeNode currentNode : this.nodeList) {
			GroupHandlerHelper currentHandlerHelper = (GroupHandlerHelper) currentNode
					.getUserObject();
			if (currentHandlerHelper.getGroupName()
					.equalsIgnoreCase(fatherName)) {
				return currentNode;
			}
		}
		return null;
	}

	private GroupHandlerHelper findRootElement(
			List<GroupHandlerHelper> groupsHandler) {
		for (GroupHandlerHelper hand : groupsHandler) {
			if (hand.getFatherName().equalsIgnoreCase("root")) {
				return hand;
			}
		}
		return null;
	}
}