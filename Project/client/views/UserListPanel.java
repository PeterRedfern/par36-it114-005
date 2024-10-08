package Project.client.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import Project.client.ICardControls;

public class UserListPanel extends JPanel {
    JPanel userListArea;
    JPanel wrapper;
    private static Logger logger = Logger.getLogger(UserListPanel.class.getName());

    public UserListPanel(ICardControls controls) {
        super(new BorderLayout(10, 10));
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentY(Component.TOP_ALIGNMENT);

        // wraps a viewport to provide scroll capabilities
        JScrollPane scroll = new JScrollPane(content);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        // scroll.setBorder(BorderFactory.createEmptyBorder());
        // no need to add content specifically because scroll wraps it

        userListArea = content;
        this.wrapper = wrapper;
        wrapper.add(scroll);
        this.add(wrapper, BorderLayout.CENTER);

        userListArea.addContainerListener(new ContainerListener() {

            @Override
            public void componentAdded(ContainerEvent e) {
                if (userListArea.isVisible()) {
                    resizeUserListItems();
                    // userListArea.revalidate();
                    // userListArea.repaint();
                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (userListArea.isVisible()) {
                    resizeUserListItems();
                    // userListArea.revalidate();
                    // userListArea.repaint();
                }
            }

        });
    }

    public void resizeUserListItems() {
        for (Component p : userListArea.getComponents()) {
            if (p.isVisible()) {
                /*
                 * p.setPreferredSize(
                 * new Dimension(wrapper.getWidth(), ClientUtils.calcHeightForText(this,
                 * ((JEditorPane) p).getText(), wrapper.getWidth())));
                 * p.setMaximumSize(p.getPreferredSize());
                 */
                // p.setPreferredSize(new Dimension(wrapper.getWidth(), 30));
                Dimension newSize = new Dimension(wrapper.getWidth(), 30);
                p.setPreferredSize(newSize);
                p.setMaximumSize(newSize);
            }
        }
        userListArea.revalidate();
        userListArea.repaint();
    }

    protected void addUserListItem(long clientId, String clientName) {
        logger.log(Level.INFO, "Adding user to list: " + clientName);
        JPanel content = userListArea;
        logger.log(Level.INFO, "Userlist: " + content.getSize());
        UserListItem uli = new UserListItem(clientName, clientId);
        Dimension newSize = new Dimension(wrapper.getWidth(), 30);
        uli.setPreferredSize(newSize);
        uli.setMaximumSize(newSize);
        /*
         * uli.setBorder(BorderFactory.createCompoundBorder(
         * BorderFactory.createLineBorder(Color.RED),
         * uli.getBorder()));
         */
        content.add(uli);
    }
    
    protected void userListNameRefresh(long clientId) {
        logger.log(Level.INFO, "updating the color of the client's name on userListPanel when they send a message" + clientId);
        Component[] cs = userListArea.getComponents(); for (Component c : cs) {
            boolean isUser = c.getName().equals(clientId + "");
            if(((UserListItem) c).getColor() != Color.GRAY) {
                ((UserListItem) c).setColor((isUser ? Color.YELLOW : Color.black)); // par36 12/6/23 - sets the user's name to yellow for last message sent
            } 
        }
    }

    protected void userListNameRefreshMute(long clientId) { //par36 - based on userListNameRefresh
        logger.log(Level.INFO, "updating the color of the client's name on userListPanel when muted" + clientId);
        Component[] cs = userListArea.getComponents(); for (Component c : cs) {
            if(c.getName().equals(clientId + "")) {
            ((UserListItem) c).setColor(Color.GRAY); // par36 12/11/23 - sets the user's name to gray when muted by another user
            }
        }
    }

    protected void userListNameRefreshUnmute(long clientId) { // par36 12/12/23 - opposite of userListNameRefreshMute
        logger.log(Level.INFO, "updating the color of the client's name on userListPanel when unmuted" + clientId);
        Component[] cs = userListArea.getComponents(); for (Component c : cs) {
            if(c.getName().equals(clientId + "")) {
            ((UserListItem) c).setColor(Color.BLACK); // par36 12/12/23 - sets the user's name to black when unmuted by another user
            }
        }
    }

    protected void removeUserListItem(long clientId) {
        logger.log(Level.INFO, "removing user list item for id " + clientId);
        Component[] cs = userListArea.getComponents();
        for (Component c : cs) {
            if (c instanceof UserListItem) {
                UserListItem u = (UserListItem) c;
                if (u.getClientId() == clientId) {
                    userListArea.remove(c);
                    break;
                }
            }
        }
    }

    protected void clearUserList() {
        Component[] cs = userListArea.getComponents();
        for (Component c : cs) {
            userListArea.remove(c);
        }
    }
}