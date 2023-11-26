package Project.common;
import java.io.Serializable;

public class Payload implements Serializable {
    // read https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;// change this if the class changes

    /**
     * Determines how to process the data on the receiver's side
     */
    private PayloadType payloadType;

    public PayloadType getPayloadType() { // par36 11/11/23 - Getter for payload type
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) { // par36 11/11/23 - Setter for payload type
        this.payloadType = payloadType;
    }

    /**
     * Who the payload is from
     */
    private String clientName;

    public String getClientName() { // par36 11/11/23 - Getter for client name
        return clientName;
    }

    public void setClientName(String clientName) { // par36 11/11/23 - Setter for client name
        this.clientName = clientName;
    }

    private long clientId;

    public long getClientId() { // par36 11/11/23 - Getter for clientID
        return clientId;
    }

    public void setClientId(long clientId) { // par36 11/11/23 - Setter for clientID
        this.clientId = clientId;
    }

    /**
     * Generic text based message
     */
    private String message;

    public String getMessage() { // par36 11/11/23 - Getter for messages
        return message;
    }

    public void setMessage(String message) { // par36 11/11/23 - Setter for messages
        this.message = message;
    }

    @Override
    public String toString() { // par36 11/11/23 - Override toString that prints out a message's information with type, cliendID/name and message type
        return String.format("Type[%s],ClientId[%s,] ClientName[%s], Message[%s]", getPayloadType().toString(),
                getClientId(), getClientName(),
                getMessage());
    }
}