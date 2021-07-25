import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordLengths {
    public void countWordLengths(FileResource fr,int[] counts){
        String[] allWords;
        for(String word : fr.words()){
            int length = word.length();
            if(Character.isLetter(word.charAt(0)) == false || 
               Character.isLetter(word.charAt(length-1)) == false){
                length -= 1;
            }
           
            if(length>counts.length){
                counts[counts.length-1] += 1;
            }
            else{
               counts[length] += 1;
            }
        }
        int moreWordsOfL = indexOfMax(counts);
        for(int i=1;i<counts.length;i++){
           System.out.println("Words with " + i + "= " + counts[i]);
        }
        System.out.println("There are more words of length " + moreWordsOfL +
                            " than any other length");
    }
    public void testCountWordsLength(){
        FileResource fr = new FileResource();
        int[] counts =  new int[31];
        countWordLengths(fr,counts);
    }
    public int indexOfMax(int[] values){
        int max = 0;
        for(int i=0;i<values.length;i++){
            if(max<values[i]){
                max = i;
            }
        }
        return max;
    }
}
