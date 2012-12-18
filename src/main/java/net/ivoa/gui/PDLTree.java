package net.ivoa.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import net.ivoa.pdl.interpreter.groupInterpreter.GroupHandlerHelper;

public class PDLTree extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTree tree;
    private GroupHandlerHelper root;
    public PDLTree(GroupHandlerHelper root, TreeSelectionListener treeListener) {


        super(new GridLayout(1, 0));
        this.root = root;
        tree = new JTree(root);
        tree.setModel(root);
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.setCellRenderer(new DisabledRenderer());


        // Listen for when the selection changes.
        tree.addTreeSelectionListener(treeListener);

        // Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        Dimension minimumSize = new Dimension(300, 300);
        treeView.setMinimumSize(minimumSize);

        // Add the split pane to this panel.
        add(treeView);



    }

    public static class DisabledRenderer extends DefaultTreeCellRenderer {
        protected final Icon disabledLeafIcon;

        protected final Icon disabledOpenIcon;

        protected final Icon disabledClosedIcon;
        public DisabledRenderer() {
            this(new GraydIcon(UIManager.getIcon("Tree.leafIcon")), new GraydIcon(
                    UIManager.getIcon("Tree.openIcon")), new GraydIcon(UIManager
                            .getIcon("Tree.closedIcon")));
        }
        public DisabledRenderer(Icon leafIcon, Icon openIcon, Icon closedIcon) {
            this.disabledLeafIcon = leafIcon;
            this.disabledOpenIcon = openIcon;
            this.disabledClosedIcon = closedIcon;
        }

        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

            super.getTreeCellRendererComponent(
                    tree, value, sel,
                    expanded, leaf, row,
                    hasFocus);
            GroupHandlerHelper ghh  = (GroupHandlerHelper) value;
            setForeground(Color.BLUE);
            setToolTipText(Boolean.toString(ghh.isGroupActive()));

            boolean treeIsEnabled = tree.isEnabled();
            boolean nodeIsEnabled = ghh.isGroupActive();
            boolean isEnabled = (treeIsEnabled && nodeIsEnabled);
            setEnabled(isEnabled);

            if (isEnabled) {
                selected = sel;
                if (leaf) {
                    setIcon(getLeafIcon());
                } else if (expanded) {
                    setIcon(getOpenIcon());
                } else {
                    setIcon(getClosedIcon());
                }
            } else {
                setForeground(Color.LIGHT_GRAY);
                selected = false;
                if (leaf) {
                    if (nodeIsEnabled) {
                        setDisabledIcon(getLeafIcon());
                    } else {
                        setDisabledIcon(disabledLeafIcon);
                    }
                } else if (expanded) {
                    if (nodeIsEnabled) {
                        setDisabledIcon(getOpenIcon());
                    } else {
                        setDisabledIcon(disabledOpenIcon);
                    }
                } else {
                    if (nodeIsEnabled) {
                        setDisabledIcon(getClosedIcon());
                    } else {
                        setDisabledIcon(disabledClosedIcon);
                    }
                }
            }


            return this;



        }
    }
    public static class GraydIcon implements Icon {
        Icon icon;

        Image image;

        public GraydIcon(Icon icon) {
            this.icon = icon;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            if (image == null) {
                Image orgImage = c.createImage(getIconWidth(), getIconHeight());
                Graphics imageG = orgImage.getGraphics();
                Color background = c.getBackground();
                imageG.setColor(background);
                imageG.fillRect(0, 0, getIconWidth(), getIconHeight());

                icon.paintIcon(c, imageG, x, y);

                ImageFilter colorfilter = new GrayFilter();
                image = c.createImage(new FilteredImageSource(orgImage.getSource(),
                        colorfilter));

            }
            g.drawImage(image, x, y, null);
        }

        public int getIconWidth() {
            return icon.getIconWidth();
        }

        public int getIconHeight() {
            return icon.getIconHeight();
        }

        public static  class GrayFilter extends RGBImageFilter {

            public GrayFilter() {
                // If I set ture, black is gone?!
                //canFilterIndexColorModel = true;
            }

            public int filterRGB(int x, int y, int rgb) {
                int r = (rgb & 0xff0000) >> 16;
                int g = (rgb & 0x00ff00) >> 8;
                int b = (rgb & 0x0000ff);
                int iy = (int) (r + g + b) / 3;
                iy = Math.min(255, iy);
                return ((rgb & 0xff000000) | (iy << 16) | (iy << 8) | iy);
            }
        }

    }

}	

