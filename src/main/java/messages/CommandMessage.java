package messages;

/**
 * @author Sarp Cagin Erdogan
 * @author Mark Ringer
 */
public class CommandMessage extends Message {
    String parameter;

    public String getParameter() {
        return parameter;
    }

    public CommandMessage(int ID, Command command) {
        super(ID, command.toString());
    }
    public CommandMessage(int ID, Command command, String parameter) {
        this.ID = ID;
        this.content = command.toString();
        this.parameter = parameter;
    }
}
