
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int N;
    public MarkovModel(int number) {
	myRandom = new Random();
	N = number;
    }
	
    public void setRandom(int seed){
	myRandom = new Random(seed);
    }
	
    public void setTraining(String s){
	myText = s.trim();
    }
	
    public String getRandomText(int numChars){
	StringBuffer sb = new StringBuffer();
	int index = myRandom.nextInt(myText.length() - N);
	String key = myText.substring(index,index + N);
	sb.append(key);
	for(int k=0; k < numChars-N; k++){
	   ArrayList<String> follows = getFollows(key);
	   if(follows.size() == 0){
	       break;
	   }
	   index = myRandom.nextInt(follows.size());
	   String next = follows.get(index);
           sb.append(next);
           key = key.substring(1) + next;
	}
	return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
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
