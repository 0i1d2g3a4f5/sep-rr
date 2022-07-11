package client_package.AI;


public class AIController extends Thread{
    public final String groupName;
    public final String protocolVersion;
    public int index = -1;
    public AIController(String protocolVersion, String groupName, int index){
        this.protocolVersion = protocolVersion;
        this.groupName = groupName;
        this.index=index;
    }

    AIClient aiClient = new BasicAI(true, this, String.valueOf(index));
    @Override
    public void run(){
        aiClient.createSocket(AIMain.IP, AIMain.PORT);
        aiClient.listen();
    }



}
