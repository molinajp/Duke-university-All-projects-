
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CaesarCipher {
    public String encrypt(String input,int key){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetL = alphabet.toLowerCase();
        String newAlphabet = alphabet.substring(key) + 
                             alphabet.substring(0,key);
        String newAlphabetL = newAlphabet.toLowerCase();
        StringBuilder sb = new StringBuilder(input);
        for(int i=0;i<sb.length();i++){
            char c = input.charAt(i);
            int indexU = alphabet.indexOf(c);
            int indexL = alphabetL.indexOf(c);
            if(indexU != -1){
                char cNewA = newAlphabet.charAt(indexU);
                sb.setCharAt(i,cNewA);                
            }
            if(indexL != -1){
                char cNewA = newAlphabetL.charAt(indexL);
                sb.setCharAt(i,cNewA);
            }
        }
        String result = sb.toString();
        return result;
    } 
    public String encryptTwoKeys(String input,int key1,int key2){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetL = alphabet.toLowerCase();
        String newAlphabet1 = alphabet.substring(key1) + 
                             alphabet.substring(0,key1);
        String newAlphabet1L = newAlphabet1.toLowerCase();
        String newAlphabet2 = alphabet.substring(key2) + 
                             alphabet.substring(0,key2);
        String newAlphabet2L = newAlphabet2.toLowerCase();
        StringBuilder sb = new StringBuilder(input);
        for(int i=0;i<sb.length();i=i+2){
            char c = input.charAt(i);
            int indexU = alphabet.indexOf(c);
            int indexL = alphabetL.indexOf(c);
            if(indexU != -1){
                char cNewA = newAlphabet1.charAt(indexU);
                sb.setCharAt(i,cNewA);
            }
            if(indexL != -1){
                char cNewA = newAlphabet1L.charAt(indexL);
                sb.setCharAt(i,cNewA);
            }
        }
        for(int i=1;i<sb.length();i=i+2){
            char c = input.charAt(i);
            int indexU = alphabet.indexOf(c);
            int indexL = alphabetL.indexOf(c);
            if(indexU != -1){
                char cNewA = newAlphabet2.charAt(indexU);
                sb.setCharAt(i,cNewA);
            }
            if(indexL != -1){
                char cNewA = newAlphabet2L.charAt(indexL);
                sb.setCharAt(i,cNewA);
            }
        }
        String result = sb.toString();
        return result;
    }
    public void testEncrypt(){
        String code = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key1 = 8;
        int key2 = 21;
        String cipher = encryptTwoKeys(code,key1,key2);
        System.out.println(cipher);
    }
    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 0;
        String encrypted = encrypt(message,key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
