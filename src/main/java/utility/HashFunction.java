package utility;

public class HashFunction {
    String chars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz123456789!§$%&/()=?`_-:.;,><|{[]}\\#'²³";
    char[] key = "235734534690742".toCharArray();

    public HashFunction(Long key) {
        this.key = String.valueOf(key).toCharArray();
    }

    public HashFunction() {

    }

    /**
     * @author Ringer
     * @param string
     * @return String
     * Hashes the string with a custom Key
     * and returns a String
     */
    public String hash(String string){
        String out= "";
        char[] inputString = string.toCharArray();

        for(int i = 0;i<inputString.length;i++){
            int place = chars.indexOf(inputString[i]);
            String numberString = String.valueOf(key[i%key.length]);
            numberString+= key[(i+1)% key.length];
            int displaceNumber = Integer.parseInt(numberString);
            out += String.valueOf(chars.charAt((place+displaceNumber)%chars.length()));
        }


        return String.valueOf(Math.pow(out.hashCode(),Double.parseDouble(String.valueOf(key[Math.abs(out.hashCode())%key.length]))));
    }

    public static void main(String[] args){
        HashFunction hf = new HashFunction();
        System.out.println(hf.hash("MarA²²56zZk"));

    }

}
