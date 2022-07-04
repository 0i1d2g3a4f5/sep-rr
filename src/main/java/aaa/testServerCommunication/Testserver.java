package aaa.testServerCommunication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Testserver {
    ServerSocket serverSocket = new ServerSocket(12345);
    Socket socket = serverSocket.accept();
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();

    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public static void main(String[] args) throws IOException {
        Testserver testserver = new Testserver();
        Thread thread = new Thread(testserver.listen);
        thread.isDaemon();
        thread.start();
        System.out.println("listener started");

    }

    void sendText(String input) throws IOException {
        input.trim();
        input += "\n";
        char[] arr = input.toCharArray();
        for (char c:arr) {
            dataOutputStream.writeInt((int)c);
        }
        dataOutputStream.flush();
    }

    Runnable listen = new Runnable() {
        @Override
        public void run() {
            try {
                while(true){
                    TimeUnit.MILLISECONDS.sleep(100);
                    String text = "";
                    while(getDataInputStream().available()>0){
                        int in = dataInputStream.readInt();
                        System.out.println(in);
                        text+= (char)in;
                    }
                    if(text !="")
                    System.out.println(text);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    };

    public Testserver() throws IOException {
    }
}
