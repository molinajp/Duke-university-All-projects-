
import edu.duke.*;

public class TestCaesarCipherTwo {
    public int[] countLetters(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i=0;i<message.length();i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int index = alphabet.indexOf(ch);
            if(index != -1){
                counts[index] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] freq){
        int result = 0;
        for(int i=0;i<freq.length;i++){
            int currIndex = freq[i];
            if(currIndex>freq[result]){
                result = i;
            }
        }
        return result;
    }
    public String halfOfString(String message,int start){
        StringBuilder sb = new StringBuilder();
        for(int i=start;i<message.length();i+=2){
            char c = message.charAt(i);
            sb.append(c);
        }
        String result = sb.toString();
        return result;
    }
    public int getKey(String s){
        int[] counts = countLetters(s);
        int maxcount = maxIndex(counts);
        return maxcount;
    }
    public String breakCaesarCipher(String input){
        String firstHalf = halfOfString(input,0);
        String secondHalf = halfOfString(input,1);
        int first = getKey(firstHalf);
        int keyFirst = first - 4;
        if(first<4){
            keyFirst = 26 - (4 - first);
        }
        int second = getKey(secondHalf);
        int keySecond = second - 4;
        System.out.println(keyFirst);
        System.out.println(keySecond);
        if(second<4){
            keySecond = 26 - (4 - second); 
        }
        CaesarCipherTwo cc = new CaesarCipherTwo(keyFirst,keySecond);
        return cc.decrypt(input);
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        String encrypted = cc.encryptTwoKeys(message);
        System.out.println("Message encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Message decrypted 1: " + decrypted);
        String decrypted2 = breakCaesarCipher(encrypted);
        System.out.println("Message decrypted 2: " + decrypted2);
    }
}
