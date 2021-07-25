
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    private int maxIndex;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String word : fr.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(! myWords.contains(word)){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    private void findIndexOfMax(){
        maxIndex = 0;
        int max = 0;
        for(int k=0;k<myFreqs.size();k++){
            int current = myFreqs.get(k);
            if(max<current){
                max=current;
                maxIndex=myFreqs.indexOf(current);
            }
        }
    }
    public void tester(){
        findUnique();
        System.out.println("# unique words:\t" + myWords.size());
        //for(int k=0;k<myWords.size();k++){
        //    System.out.println("The word '" + myWords.get(k) + "' appears " +
        //                        myFreqs.get(k)+ " times");  
        //}
        findIndexOfMax();
        System.out.println("The most reapeted word is '" + 
                           myWords.get(maxIndex) + "' and is repeated " + 
                           myFreqs.get(maxIndex) + " times");
    }
}
