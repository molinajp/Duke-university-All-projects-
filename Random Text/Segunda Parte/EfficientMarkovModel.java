
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap<String,ArrayList<String>> map;
    public EfficientMarkovModel(int number) {
        myRandom = new Random();
        N = number;
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public String toString(){
        return "EfficientMarkovModel class of order " + N;
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
    
    public void buildMap(){
        for(int k=0;k<myText.length()-N+1;k++){
             String key = myText.substring(k, k + N);
             ArrayList<String> follows = new ArrayList<String>();
             if(k+N >= myText.length()){
                 map.put(key,follows);
             }
             else{
                 if(map.containsKey(key)){
                     map.get(key).add(myText.substring(k+N,k+N+1));
                 }
                 else{
                     follows.add(myText.substring(k+N,k+N+1));
                     map.put(key,follows);
                 } 
             }
        }
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> answer = new ArrayList<String>();
        answer = map.get(key);
        return answer;
    }
    
    public void printHashMapInfo(){
        /*for(String s : map.keySet()){
            ArrayList<String> array = map.get(s);
            System.out.println(s + array);
        }*/
        System.out.println(map.size());
        int largest = 0;
        for(String s : map.keySet()){
            ArrayList<String> array = map.get(s);
            if(array.size() > largest){
                largest = array.size();
            }
        }
        System.out.println("Largest size of array is " + largest);
        String keys = "";
        for(String s : map.keySet()){
            if(map.get(s).size() == largest){
                keys = keys + s + " ";
            }
        }
        System.out.println(keys);
    }
}
