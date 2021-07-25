
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public boolean isVowel(char ch){
        boolean value = false;
        String vowels = "AEIOUaeiou";
        for(int i = 0; i<vowels.length(); i++){
            char c = vowels.charAt(i);
            if (c==ch){
                value = true;
            }
        }
        return value;
    }
    public String replaceVowels(String phrase,char ch){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<phrase.length();i++){
            char c = phrase.charAt(i);
            boolean isIt = isVowel(c);
            if(isIt == true){
                sb = sb.append(ch);
            }
            else{
                sb = sb.append(c);
            }
        }
        String result = sb.toString();
        return result;
    }
    public String emphazise(String phrase,char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
           char c = phrase.charAt(i);
           char cL = Character.toLowerCase(c);
           char cU = Character.toUpperCase(c);
           if(cL == ch || cU == ch){
              if(i%2 == 0){
                sb.setCharAt(i,'*');
              }
              else{
                 if(i%2 != 0){
                    sb.setCharAt(i,'+');
                 }
              }
           }
           else{
               sb.setCharAt(i,c);
           }
        }
        String result = sb.toString();
        System.out.println(result);
        return result;
    }
    public void testReplaceVowels(){
        String test = "Hello World";
        char ch = '*';
        String newPhrase = replaceVowels(test,ch);
        System.out.println(newPhrase);
        emphazise("dna ctgaaactga", 'a');
    }
}