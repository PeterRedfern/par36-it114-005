package Project.client.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel; 

import Project.client.ClientUtils;
public class UserListItem extends JPanel {
    private long clientId;
    private String clientName;
    JEditorPane text = new JEditorPane("text/plain", "");
    JButton hostIndicator = new JButton();
    JButton outIndicator = new JButton("x");

    public UserListItem(String clientName, long clientId) {
        this.clientId = clientId;
        this.setName(clientId+"");
        this.clientName = clientName;
        hostIndicator.setEnabled(false);
        hostIndicator.setVisible(false);
        hostIndicator.setBackground(Color.YELLOW);
        outIndicator.setEnabled(false);
        outIndicator.setBackground(Color.RED);
        outIndicator.setVisible(false);
        Dimension d = new Dimension(24, 24);
        hostIndicator.setPreferredSize(d);
        hostIndicator.setMinimumSize(d);
        hostIndicator.setMaximumSize(d);
        outIndicator.setPreferredSize(d);
        outIndicator.setMinimumSize(d);
        outIndicator.setMaximumSize(d);
        // setBackground(Color.BLUE);

        text.setEditable(false);
        text.setText(getBaseText());
        this.add(hostIndicator);
        this.add(outIndicator);
        this.add(text);
        ClientUtils.clearBackground(text);
    }

    private String getBaseText() {
        return String.format("%s", clientName, clientId);
    }

    public long getClientId() {
        return clientId;
    }

    public void setColor(Color Color) { // par36 12/8/23 - Changes the user's name to a different color 
        text.setForeground(Color); 
    }
}