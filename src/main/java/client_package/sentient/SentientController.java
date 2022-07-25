package client_package.sentient;

import org.apache.log4j.Logger;
import utility.GlobalParameters;

import java.io.IOException;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SentientController {


    public Logger logger = Logger.getLogger("AILogger");
    SentientClient sentientClient;
    public SentientController(){
        logger.info("Created new SentientController with protocol version " + GlobalParameters.PROTOCOL_VERSION + ".");
        this.sentientClient=new SentientClient(this);
    }


    public void start(String name){
       sentientClient.sentientBehaviour.start(name);
    }

    public void start(String name, String string, int port, String group){
        sentientClient.sentientBehaviour.start(name, string, port, group);
    }
    public Logger getLogger() {
        return logger;
    }

}
