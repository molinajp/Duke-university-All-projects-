
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int k=whichSlice;k<message.length();k+=totalSlices){
            char c = message.charAt(k);
            sb.append(c);
        }
        String result = sb.toString();
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon){
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int k=0;k<klength;k++){
            String slice = sliceString(encrypted,k,klength);
            int keyNumber = cc.getKey(slice);
            key[k] = keyNumber;
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        HashMap<String,HashSet<String>> langs = 
        new HashMap<String,HashSet<String>>();
        String[] dictionaries = {"English","Danish","Dutch","French","German",
                                 "Italian","Portuguese","Spanish"};
        for(int k=0;k<dictionaries.length;k++){
            FileResource fr2 = new FileResource("dictionaries/" + 
                                                 dictionaries[k]);
            HashSet<String> dictionary = readDictionary(fr2);
            langs.put(dictionaries[k],dictionary);
            System.out.println(dictionaries[k] + " is read");
        }
        //String decrypted = breakForLanguage(message,dictionary);
        //System.out.println(decrypted);
        breakForAllLangs(message,langs);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<String>();
        for(String s : fr.lines()){
            String sL = s.toLowerCase();
            set.add(sL);
        }
        return set;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        int count = 0;
        String[] split = message.split("\\W+");
        for(int k=0;k<split.length;k++){
            String word = split[k].toLowerCase();
            if(dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted,
                                   HashSet<String> dictionary){
        String result = null;
        int max = 0;
        int[] finalKeyLength = null;
        int validWords = 0;
        int totalWords = 0;
        for(int k=1;k<100;k++){
            char ch = mostCommonCharIn(dictionary);
            int[] keyLength = tryKeyLength(encrypted,k,ch);
            VigenereCipher vc = new VigenereCipher(keyLength);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted,dictionary);
            if(count>max){
                max = count;
                result = decrypted;
                finalKeyLength = keyLength;
                validWords = count;
            }
        }
               
        System.out.println("The length of the key is " + 
                           finalKeyLength.length);
        System.out.println("There are " + validWords + " valid words");
        return result;
    }
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(String s : dictionary){
            for(int k=0;k<s.length();k++){
                char c = s.charAt(k);
                if(! map.containsKey(c)){
                    map.put(c,1);
                }
                else{
                    int current = map.get(c);
                    map.put(c,current+1);
                }
            }
        }
        int max = 0;
        char result = '+';
        for(Character i : map.keySet()){
            int current = map.get(i);
            if(current>max){
                max = current;
                result = i;
            }
        }
        return result;
    }
    public void breakForAllLangs(String encrypted, 
                                 HashMap<String,HashSet<String>> languages){
       String result = null;
       String language = null;
       int max = 0;
       for(String s : languages.keySet()){
           HashSet<String> currentLang = languages.get(s);
           String decrypted = breakForLanguage(encrypted,currentLang);
           int words = countWords(decrypted,currentLang);
           if(words>max){
               max = words;
               result = decrypted;
               language = s;
           }
       }
       System.out.println(result);
       System.out.println("The language is " + language);
    }
}
