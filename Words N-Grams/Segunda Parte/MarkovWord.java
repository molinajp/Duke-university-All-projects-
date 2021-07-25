
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words,WordGram target,int start){
        for(int k=start;k<words.length;k++){
            if(k >= words.length - myOrder){
                break;
            }
            WordGram other = new WordGram(words,k,target.length());
            if(other.equals(target)){
                return k;
            }
        }
        return -1;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim(); 
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length - myOrder){
            int start = indexOf(myText,kGram,pos);
            if(start == -1){
                break;
            }
            if(start+kGram.length() >= myText.length - myOrder){
                break;
            }
            String next = myText[start+myOrder];
            follows.add(next);
            pos = start + 1;
        }
        return follows;
    }
    
    public void testIndexOf(){
        String[] test = {"this","is","just","a","test","yes","this","is","a",
                         "simple","test"};
        WordGram wg = new WordGram(test,0,3);
        System.out.println(indexOf(test,wg,5));
    }
}
