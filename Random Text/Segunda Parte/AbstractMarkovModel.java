
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while(true){
            int index = myText.indexOf(key,pos);
            if(index + key.length() + 1> myText.length()){
                break;
            }
            pos = index + 1;
            if(index != -1){
                follows.add(myText.substring(index+key.length(), 
                                             index+key.length()+1));
            }
            else{
                break;
            }
        }
        return follows;
    }
    
}