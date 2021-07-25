
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFile {
   private HashMap<String,ArrayList<String>> map;
   ArrayList<String> list;
   public WordsInFile(){
        map = new HashMap<String,ArrayList<String>>();
        list = new ArrayList<String>();
   }
   private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            word = word.toLowerCase();
            
            if(! map.containsKey(word)){
                ArrayList<String> sArray = new ArrayList<String>();
                sArray.add(f.getName());
                map.put(word,sArray);
            }
            else{
               ArrayList<String> sArray = map.get(word);
               if(! sArray.contains(f.getName())){
                  sArray.add(f.getName());
                  map.put(word,sArray);
               }
            }
        }
   }
   private void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
   }
   private int maxNumber(){
        int max = 0;
        ArrayList<String> array = new ArrayList<String>();
        for(String s : map.keySet()){
            if(! array.contains(map.get(s))){
               array = map.get(s);
               
            }
            
            int current = array.size();
            if(current>max){
                max = current;
            }
        }
        return max;
   }
   private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> result = new ArrayList<String>();
        for (String s : map.keySet()){
            ArrayList<String> array = map.get(s);
            if(array.size() == number){
                result.add(s);
            }
        }
        return result;
   }
   private void printFilesIn(String word){
       for(String s : map.keySet()){
           if(map.containsKey(word)){
               if(! list.contains(map.get(word))){
                  list = map.get(word);
               }
           }
       }
       for(int k=0;k<list.size();k++){
             System.out.println("The word " + "'" + word + "'" + 
                                " appears in " + list.get(k));
       }
   }
   public void tester(){
       buildWordFileMap();
       int maxNumber = maxNumber();
       
       ArrayList<String> allWords = wordsInNumFiles(4);
       System.out.println(allWords.size());
       
       printFilesIn("sea");
       
       //for(int k=0;k<allWords.size();k++){
       //   printFilesIn(allWords.get(k));
       //}
       
       
       //for(int k=0;k<allWords.size();k++){
       //    System.out.println(allWords.get(k));
       //}
       //for(String s : map.keySet()){
        //   System.out.println(s + ":\t" + map.get(s));
        //   
       //}
   }
   
}  
