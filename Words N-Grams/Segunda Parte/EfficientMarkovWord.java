
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram,ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
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
            sb.append(" ");//puede venir algo más acá
            key = key.shiftAdd(next);
        }
        return sb.toString().trim(); 
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        for(WordGram wg : map.keySet()){
            if(kGram.equals(wg)){
                follows = map.get(wg);
                break;
            }
        }
        return follows;
    }

    public void buildMap(){
        for(int i=0;i<myText.length - myOrder;i++){
            WordGram wg = new WordGram(myText,i,myOrder);
            ArrayList<String> follows = new ArrayList<String>();
            int pos = i;
            while(true){
                int k = indexOf(myText,wg,pos);
                if(k == -1 || k == myText.length - myOrder){
                    break;
                }
                follows.add(myText[k+myOrder]);
                pos = k + myOrder;
            }
            int temp = 0;
            for(WordGram g : map.keySet()){
                if(g.equals(wg)){
                    temp = 1;
                    break;
                }
            }
            if(temp == 0){
                map.put(wg,follows);   
            }
        }
    }
   
    public void printHashMapInfo(){
        /*for(WordGram s : map.keySet()){
            ArrayList<String> array = map.get(s);
            System.out.print(s);
            System.out.println(array);
        }*/
        System.out.println(map.size());
        int largest = 0;
        for(WordGram s : map.keySet()){
            ArrayList<String> array = map.get(s);
            if(array.size() > largest){
                largest = array.size();
            }
        }
        System.out.println("Largest size of array is " + largest);
        String keys = "";
        for(WordGram s : map.keySet()){
            if(map.get(s).size() == largest){
                keys = keys + s + " ";
            }
        }
        System.out.println(keys);
    }
}

