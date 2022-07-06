package aexperimental.testServerCommunication;

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
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(dataOutputStream));

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

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
        bufferedWriter.write(input);
        bufferedWriter.newLine();
        bufferedWriter.write("|");
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
                    if(dataInputStream.available()>0)
                        System.out.println("avaiable:" +dataInputStream.available());
                    TimeUnit.MILLISECONDS.sleep(100);
                    String text = "";
                    boolean isReading = true;
                    int readChars =dataInputStream.available();

                        while(isReading && readChars>0){
                            String input = bufferedReader.readLine();
                            System.out.println("input:" +input);
                            if(input.equals("\n" )|| input.equals("")){
                                System.out.println("ended");
                                isReading = false;
                            }

                            else
                                text += input;
                            readChars--;
                        }


                        /*
                        int in = dataInputStream.readInt();
                        System.out.println(in);
                        text+= (char)in;

                         */

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
