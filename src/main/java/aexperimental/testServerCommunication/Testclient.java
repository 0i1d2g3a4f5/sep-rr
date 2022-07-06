package aexperimental.testServerCommunication;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Testclient {
    Socket socket = new Socket("localhost",12345);
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();

    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
    DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));

    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
    public static void main(String[] args) throws IOException {
        Testclient testclient = new Testclient();
        Thread thread = new Thread(testclient.listen);
        thread.isDaemon();
        thread.start();
        System.out.println("listener started");

        testclient.sendText("Hello Server ");
        testclient.sendText("Hello Server ");

        testclient.socket.close();



    }

    void sendText(String input) throws IOException {
        input.trim();
        input += "\n";
        System.out.println("SENT: "+input);
        bufferedWriter.write(input);

        bufferedWriter.write("\n");
        bufferedWriter.flush();
        /*
        char[] arr = input.toCharArray();
        for (char c:arr) {
            dataOutputStream.writeInt((int)c);
        }

        dataOutputStream.flush();

         */
    }

    Runnable listen = new Runnable() {
        @Override
        public void run() {
            try {
                while(true){
                    TimeUnit.MILLISECONDS.sleep(100);
                    String text = "";
                    while(dataInputStream.available()>0){
                        text = bufferedReader.readLine();
                        /*
                        text+= (char)dataInputStream.readInt();

                         */

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


    public Testclient() throws IOException {
    }
}

