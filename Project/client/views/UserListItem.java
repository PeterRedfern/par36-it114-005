package Project.client.views;

import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import Project.client.ClientUtils;

public class UserListItem extends JPanel {
    private long clientId;
    private String clientName;
    JEditorPane text = new JEditorPane("text/html", "");

    public UserListItem(String clientName, long clientId) {
        this.clientId = clientId;
        this.clientName = clientName;
        // setBackground(Color.BLUE);

        text.setEditable(false);
        text.setText(getBaseText());

        this.add(text);
        ClientUtils.clearBackground(text);
    }

    private String getBaseText() {
        return String.format(clientName, clientId);
    }

    public long getClientId() {
        return clientId;
    }

    public void mutedClient() {
        if(User == isMuted) {
            clientName = "*$" + clientName + "$*"; 
        }
    }

    public void setSeeker(long clientId) {
        if (this.clientId == clientId) {
            this.setBackground(Color.CYAN);
        } else {
            this.setBackground(Color.WHITE);
        }
    }
}
