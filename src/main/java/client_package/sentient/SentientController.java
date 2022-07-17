package client_package.sentient;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SentientController {


    public Logger logger = Logger.getLogger("AILogger");
    SentientClient sentientClient;
    String protocolVersion;
    public SentientController(){
        protocolVersion="Version 2.0";
        logger.info("Created new SentientController with protocol version " + getProtocolVersion() + ".");
        this.sentientClient=new SentientClient(this);
    }


    public void start(String name){
       sentientClient.start(name);
    }

    public void start(String name, String string, int port, String group){
        sentientClient.start(name, string, port, group);
    }





    public String getProtocolVersion() {
        return protocolVersion;
    }
    public Logger getLogger() {
        return logger;
    }

}
