package client;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */
public class HashSystem {
    Client client;
    String filePath;
    File file;
    FileWriter fileWriterAppend, fileWriterOverwrite;
    FileReader fileReader;
    BufferedReader bufferedReader;
    List<Integer> hashes = new ArrayList<Integer>();
    List<Integer> oldHashes = new ArrayList<Integer>();

    public HashSystem(Client client){
        this.client=client;
        filePath=Paths.get("").toAbsolutePath().normalize().toString()+"\\src\\main\\resources\\Hashes.txt";
        file = new File(filePath);
        boolean created;
        try {
            created = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!created){
            file= new File(filePath);
        }
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriterAppend = new FileWriter(file, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        oldHashes=readHashes();

    }




    public void createNewHashes(String string){
        clearHashes();
        for(int i=0; i<string.length(); i++){
            writeHash(createSingleHash(i));
        }
    }

    public int createSingleHash(int index){
        int random = (int)(Math.random()*Math.pow(2, 16));
        hashes.add(index, random);
        //System.out.println("Hash at " + index + " is: " + random);
        return random;
    }
    public void writeHash(int hash){
        String string = String.valueOf(hash);
        try {
            fileWriterAppend.append(string + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            fileWriterAppend.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Integer> readHashes() {
        List<Integer> temp = new ArrayList<>();
        int i = 0;
        String s;
        while (true){
            try {
                if((s = bufferedReader.readLine())!=null){
                    temp.add(i, Integer.valueOf(s));
                    i++;
                }
                else break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return temp;
    }

    public String encodeString(String string, List<Integer> list){
        String result="";
        for(int i=0; i<string.length(); i++){
            char temp = (char)(((int)(string.charAt(i)) + list.get(i))%(int)Math.pow(2, 16));
            result+=Character.toString(temp);
        }
        return result;
    }

    public void clearHashes(){
        this.hashes.clear();
        try {
            fileWriterOverwrite = new FileWriter(file, false);
            fileWriterOverwrite.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
