
import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part2 {
    void countShakespeare(){
        String[] plays = {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int i=0;i<plays.length;i++){
            FileResource fr = new FileResource("Common Words Data/"+ plays[i]);
            countWords(fr,common,counts);
            System.out.println("done with " + plays[i]);
        }
        for(int i=0;i<common.length;i++){
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }
    public String[] getCommon(){
        FileResource fr = new FileResource("Common Words Data/common.txt");
        String[] result = new String[20];
        int index = 0;
        for(String s : fr.words()){
            result[index] = s;
            index += 1;
        }
        return result;
    }
    public void countWords(FileResource fr, String[] common, 
                           int[] counts){
       for(String word : fr.words()){
           word = word.toLowerCase();
           int index = indexOf(common,word);
           if(index != -1){
               counts[index] += 1;
           }
       }
    }
    public int indexOf(String[] list,String word){
        for(int i=0; i<list.length;i++){
            if(list[i].equals(word)){
                return i;
            }
        }
        return -1;
    }
}
