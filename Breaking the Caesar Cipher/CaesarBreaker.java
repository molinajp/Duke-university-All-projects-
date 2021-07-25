 



import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.Object.*;


public class CaesarBreaker {
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
    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted,0);
        String secondHalf = halfOfString(encrypted,1);
        int first = getKey(firstHalf);
        int keyFirst = first - 4;
        if(first<4){
            keyFirst = 26 - (4-first);
        }
        int second = getKey(secondHalf);
        int keySecond = second - 4;
        if(second<4){
            keySecond= 26 - (4-second);
        }
        System.out.println("The first key is: " + keyFirst);
        System.out.println("The second key is: " + keySecond);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted,26-keyFirst,26-keySecond);
    }
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        message = decryptTwoKeys(message);
        System.out.println(message);
    }
}
