package Project.client.views;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.FileWriter; // par36 12/6/23 - Imported for fileWriter to write export chat history
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate; // par36 12/12/23 - imported for date in export file name
import java.time.LocalTime; // par36 12/12/23 - imported for time in export file name

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Project.client.Card;
import Project.client.Client;
import Project.client.ClientUtils;
import Project.client.ICardControls;

public class ChatPanel extends JPanel {
    private static Logger logger = Logger.getLogger(ChatPanel.class.getName());
    private JPanel chatArea = null;
    private JPanel wrapper = null;
    private UserListPanel userListPanel;
    private Dimension lastSize = new Dimension();

    public ChatPanel(ICardControls controls) {
        super(new BorderLayout(10, 10));
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        // wraps a viewport to provide scroll capabilities
        JScrollPane scroll = new JScrollPane(content);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        // no need to add content specifically because scroll wraps it
        wrapper.add(scroll);
        this.add(wrapper, BorderLayout.CENTER);

        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
        JTextField textValue = new JTextField();
        input.add(textValue);
        JButton button = new JButton("Send");
        JButton export = new JButton("Export Chat"); // par36 12/7/23 - Export chat button
        // lets us submit with the enter key instead of just the button click
        textValue.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        button.addActionListener((event) -> {
            try {
                String text = textValue.getText().trim();
                if (text.length() > 0) {
                    Client.INSTANCE.sendMessage(text);
                    textValue.setText("");// clear the original text

                    // debugging
                    logger.log(Level.INFO, "Content: " + content.getSize());
                    logger.log(Level.INFO, "Parent: " + this.getSize());

                }
            } catch (NullPointerException e) {
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        chatArea = content;
        this.wrapper = wrapper;
        input.add(button);
        userListPanel = new UserListPanel(controls);
        this.add(userListPanel, BorderLayout.EAST);
        this.add(input, BorderLayout.SOUTH);
        this.add(export, BorderLayout.NORTH); // par36 12/6/23 - adds the export button at the top of the chat
        this.setName(Card.CHAT.name());
        controls.addPanel(Card.CHAT.name(), this);

        export.addActionListener((event) -> { // par36 12/7/23 - export chat method
            {
                try {
                    LocalDate date = LocalDate.now(); // par36 12/12/23 - gets the date
                    LocalTime time = LocalTime.now(); // gets the time 
                    String timeString = time.toString(); // makes the time message into a String
                    timeString = timeString.replace(":", "."); // par36 12/12/23 - changes the : in the time message so it can ouput to a file without error
                    FileWriter fileWriter = new FileWriter("Project/chatexport/" + "ChatExport " + date + " " + timeString + ".html"); //writes the output of the chat to an HTML file
                    fileWriter.write(getChatHistory());
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        chatArea.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                if (chatArea.isVisible()) {
                    chatArea.revalidate();
                    chatArea.repaint();
                    /**
                     * Note: with the setValue(maxValue) it seemed to have a gap.
                     * The gap would cut off the last message.
                     * The updated logic below from https://stackoverflow.com/a/34086741
                     * solves this.
                     */
                    JScrollBar vertical = ((JScrollPane) chatArea.getParent().getParent()).getVerticalScrollBar();
                    vertical.setValue(vertical.getMaximum());
                    AdjustmentListener scroller = new AdjustmentListener() {
                        @Override
                        public void adjustmentValueChanged(AdjustmentEvent e) {
                            Adjustable adjustable = e.getAdjustable();
                            adjustable.setValue(vertical.getMaximum());
                            // We have to remove the listener, otherwise the
                            // user would be unable to scroll afterwards
                            vertical.removeAdjustmentListener(this);
                        }

                    };
                    vertical.addAdjustmentListener(scroller);

                }
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                if (chatArea.isVisible()) {
                    chatArea.revalidate();
                    chatArea.repaint();
                }
            }

        });
        wrapper.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {

                super.componentShown(e);
                logger.log(Level.INFO, "Component shown");

                doResize();
            }

            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized to " + e.getComponent().getSize());
                // rough concepts for handling resize
                // set the dimensions based on the frame size
                doResize();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });
    }

    public UserListPanel getUserListPanel() {
        return userListPanel;
    }

    public void highlightUser(long clientId) { // par36 12/6/23 - highlights the user that speaks last in yellow
        userListPanel.userListNameRefresh(clientId);
    }

    public void muteHighlight(long clientId) { // par36 12/6/23 - highlights the user that is muted in gray
        userListPanel.userListNameRefreshMute(clientId);
    }

    public void unmuteHighlight(long clientId) { // par36 12/12/23 - the user that is unmuted reverts to normal color
        userListPanel.userListNameRefreshUnmute(clientId);
    }

    private void doResize() {
        if (!this.isVisible()) {
            return;
        }
        Dimension frameSize = wrapper.getSize();
        int deltaX = Math.abs(frameSize.width - lastSize.width);
        int deltaY = Math.abs(frameSize.height - lastSize.height);
        if (deltaX >= 5 || deltaY >= 5) {
            lastSize = frameSize;
            // see if WEST slot is in use
            Component c = ((BorderLayout) this.getLayout()).getLayoutComponent(BorderLayout.WEST);
            float leftPanel = .5f;
            float centerPanel = .2f;
            float rightPanel = .3f;
            int width = (int) Math.max(frameSize.getWidth(), 600);
            int leftPanelWidth = (int) (width * leftPanel);
            int centerPanelWidth = (int) (width * centerPanel);
            int rightPanelWidth = (int) (width * rightPanel);
            Dimension left = new Dimension(leftPanelWidth, (int) frameSize.getHeight());
            Dimension center = new Dimension(centerPanelWidth, (int) frameSize.getHeight());
            Dimension right = new Dimension(rightPanelWidth, (int) frameSize.getHeight());

            if (c != null) {
                if (c.getWidth() != left.getWidth()) {
                    c.setMinimumSize(left);
                    c.setPreferredSize(left);
                }
            }
            logger.info("Wrapper size: " + frameSize);
            if (userListPanel.getWidth() != right.getWidth()) {
                userListPanel.setMinimumSize(right);
                userListPanel.setPreferredSize(right);
                userListPanel.resizeUserListItems();
            }

            userListPanel.revalidate();
            userListPanel.repaint();
            int originalWidth = chatArea.getWidth();
            if (chatArea.getWidth() != center.getWidth()) {
                chatArea.setMinimumSize(center);
            }
            if (Math.abs(originalWidth - chatArea.getWidth()) > 10) {
                resizeMessages();
            }

            // scroll down on new message
            JScrollBar vertical = ((JScrollPane) chatArea.getParent().getParent()).getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        }
    }

    private void resizeMessages() {
        for (Component p : chatArea.getComponents()) {
            if (p.isVisible()) {
                p.setPreferredSize(
                        new Dimension(wrapper.getWidth(), ClientUtils.calcHeightForText(this,
                                ((JEditorPane) p).getText(), wrapper.getWidth())));
                p.setMaximumSize(p.getPreferredSize());
            }
        }
        chatArea.revalidate();
        chatArea.repaint();
    }

    public void addUserListItem(long clientId, String clientName) {
        userListPanel.addUserListItem(clientId, clientName);
    }

    public void removeUserListItem(long clientId) {
        userListPanel.removeUserListItem(clientId);
    }

    public void clearUserList() {
        userListPanel.clearUserList();
    }

    public void addText(String text) {
        addText(text, Color.BLACK);
    }

    public String getChatHistory() { // par36 12/7/23 - created to get chatHistory for export function
        StringBuilder builder = new StringBuilder();
        Component[] cs = chatArea.getComponents();
        for (Component c : cs) {
            String m = ((JEditorPane) c).getText();
            builder.append(m);
            builder.append("<br />");
        }
        return builder.toString();
    }

    public void addText(String text, Color color) {
        JPanel content = chatArea;
        // add message
        JEditorPane textContainer = new JEditorPane("text/html", text); // par36 11/17/23 - Changed to "text/html" to process new fonts
        // sizes the panel to attempt to take up the width of the container
        // and expand in height based on word wrapping
        textContainer.setLayout(null);
        Dimension newDimension = new Dimension(content.getWidth(),
                ClientUtils.calcHeightForText(this, text, content.getWidth()));
        textContainer.setPreferredSize(newDimension);
        textContainer.setMaximumSize(newDimension);
        textContainer.setMinimumSize(newDimension);
        textContainer.setEditable(false);
        ClientUtils.clearBackground(textContainer);
        textContainer.setForeground(color);
        // add to container and tell the layout to revalidate
        content.add(textContainer);
        // scroll down on new message
        JScrollBar vertical = ((JScrollPane) chatArea.getParent().getParent()).getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}