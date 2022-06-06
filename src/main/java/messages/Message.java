package messages;

import java.util.Objects;
/**
 * @author Sarp Cagin Erdogan
 * @author Mark Ringer
 */
public abstract class Message {
    public int ID;
    public String content;
    public Message(int ID,String content){
        this.ID = ID;
        this.content = content;
    }

    protected Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "ID=" + ID +
                ", content='" + content + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return getID() == message.getID() && Objects.equals(getContent(), message.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getContent());
    }
}
