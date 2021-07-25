
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    public CharactersInPlay(){
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    private void update(String person){
        if(! names.contains(person)){
            names.add(person);
            counts.add(1);
        }
        else{
            int index = names.indexOf(person);
            int value = counts.get(index);
            counts.set(index,value+1);
        }
    }
    public void findAllCharacters(){
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for(String lines : fr.lines()){
           int indexPoint = lines.indexOf('.');
           if(indexPoint != -1){
              String name = lines.substring(0,indexPoint);
              update(name);
           }
        }
    }
    public void charactersWithNumParts(int num1,int num2){
        for(int k=0;k<counts.size();k++){
            int number = counts.get(k);
            if(number>=num1 && number<=num2){
                System.out.println(names.get(k) + 
                                  " has speaking parts between " + num1 + 
                                  " and " + num2);
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int k=0;k<names.size();k++){
            if(counts.get(k)>1){
               System.out.println("This main Character: " + names.get(k) +
                                  " speaks " + counts.get(k) + " times");
            }
        }
        charactersWithNumParts(10,15);
    }
}
