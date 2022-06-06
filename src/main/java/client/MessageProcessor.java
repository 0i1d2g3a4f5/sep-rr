package client;

import messages.Command;
import messages.CommandMessage;
import messages.Message;

import java.io.IOException;
/**
 * @author Sarp Cagin Erdogan
 */
public class MessageProcessor {
    Client client;
    MessageProcessor(Client client){
        this.client=client;
    }
    public void process(Message input) throws IOException {
        switch (input.getClass().getSimpleName()){

            case "CommandMessage" -> {
                CommandMessage commandMessage = (CommandMessage) input;
                System.out.println("server received command message: " + commandMessage.content + " " + commandMessage.getParameter());
                handleCommand(client, Command.parseCommand(commandMessage.content), commandMessage.getParameter());
            }

            default -> {

            }
        }


    }
    void handleCommand(Client client, Command command, String parameter) throws IOException {
        switch (command){
            case ERROR -> {
            }

            default -> {throw new IllegalStateException("Unexpected value: " + command);}
        }
    }

}
