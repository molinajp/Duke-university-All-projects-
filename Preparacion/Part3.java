 



import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.Object.*;


public class Part3 {
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
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freq = countLetters(encrypted);
        int maxIndex = maxIndex(freq);
        int dKey = maxIndex - 4;
        if(maxIndex<4){
            dKey = 26 - (4-maxIndex);
        }
        return cc.encrypt(encrypted,26-dKey);       
    }
    public void testDecrypt(){
        String message = decrypt("mmmmmmmi");
        System.out.println(message);
    }
}
