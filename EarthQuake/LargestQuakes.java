
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*for(QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        /*int indexLargestMagnitude = indexOfLargest(list);
        System.out.println(indexLargestMagnitude);
        System.out.println(list.get(indexLargestMagnitude).getMagnitude());*/
        ArrayList<QuakeEntry> largestList = getLargest(list,50);
        for(QuakeEntry qe : largestList){
            System.out.println(qe);   
        }
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int answer = 0;
        double max = 0;
        for(int k=0;k<data.size();k++){
            QuakeEntry qe = data.get(k);
            double current = qe.getMagnitude();
            if(current>max){
                max = current;
                answer = k;
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,
                                            int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for(int k=0;k<howMany;k++){
            int maxIndex = indexOfLargest(copy);
            answer.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return answer;
    }
}
