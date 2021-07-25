import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String,ArrayList<String>> map;
	private ArrayList<String> usedWords;
	private ArrayList<String> categories;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
	    map = new HashMap<String,ArrayList<String>>();	
	    initializeFromSource(dataSourceDirectory);
	    myRandom = new Random();
	    usedWords = new ArrayList<String>();
	    categories = new ArrayList<String>();
	}	
	public GladLibMap(String source){
	    map = new HashMap<String,ArrayList<String>>();
	    initializeFromSource(source);
	    myRandom = new Random();
            usedWords = new ArrayList<String>();
	    categories = new ArrayList<String>();
	}
	
	private void initializeFromSource(String source) {
	    String[] labels = {"adjective","noun","country","color","name",
	                       "animal","timeframe","verb","fruit"};	
	    for(String s : labels){
	        ArrayList<String> list = readIt(source+"/"+s+".txt");
	        map.put(s,list);
	    }
	}
	
	private String randomFrom(ArrayList<String> source){
	       int index = myRandom.nextInt(source.size());
	       return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return randomFrom(map.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		
		if (first == -1 || last == -1){
			return w;
		}
		
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		if(! categories.contains(w.substring(first+1,last))){
		    categories.add(w.substring(first+1,last));
		}
		String sub = getSubstitute(w.substring(first+1,last));
		
		
		int index = usedWords.indexOf(sub);
		
		if(index == -1){
		    usedWords.add(sub);
		}    
		else{
		    return processWord(w);
                }
                return prefix+sub+suffix;
        }
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
			     story = story + processWord(word) + " ";
			 }
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	private int totalWordsInMap(){
	    int total = 0;
	    for(String s: map.keySet()){
	        ArrayList<String> list = map.get(s);
	        int size = list.size();
	        for(int k=0;k<size;k++){
	            total += 1;
	        }
	    }
	    return total;
	}
	private int totalWordsConsidered(){
	    int total = 0;
	    for(String s : categories){
	        if(map.containsKey(s)){
	            ArrayList<String> list = map.get(s);
	            int size = list.size();
	            for(int k=0;k<size;k++){
	                total += 1;
	            }
	        }
	    }
	    return total;
	}
	public void makeStory(){
	    usedWords.clear();
	    System.out.println("\n");
	    	String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n" + "Words used are: " + 
		                    usedWords.size());
		
		System.out.println("\nThere are " + totalWordsInMap() + 
		                   " words to pick from");
		System.out.println("\nThere were " + totalWordsConsidered() +
		                   " words CONSIDERED to pick from");
	}
}