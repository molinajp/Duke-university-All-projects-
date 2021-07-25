
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> map;
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    private void buildCodonMap(int start,String dna){
        map.clear();
        for(int k=0;k<dna.length();k++){
            String codon = dna.substring(start,start+3);
            start = start + 3;
            if(! map.containsKey(codon)){
                map.put(codon,1);
            }
            else{
                map.put(codon,map.get(codon)+1);
            }
            if(start>dna.length()-3){
                break;
            }
        }
    }
    private String getMostCommonCodon(){
        int largest = 0;
        String result = null;
        for(String codon : map.keySet()){
            int number = map.get(codon);
            if(number>largest){
                largest = number;
                result = codon;
            }
        }
        return result;
    }
    private void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between " + start + 
                                   " and " + end + " are: ");
        for(String s : map.keySet()){
            int counts = map.get(s);
            if(start <= counts && counts <= end){
                System.out.println(s + ":\t" + counts);
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna1 = fr.asString();
        String dna = dna1.trim();
        int start = 0;
        while(start<3){
            buildCodonMap(start,dna);
            System.out.println("Reading map starting with " + start + 
                               " results in " + map.size() + " codons");
            String common = getMostCommonCodon();
            System.out.println("Most common codon is " + common + 
                               " with count " + map.get(common));
            printCodonCounts(7,7);
            start += 1;
        }
    }
}
