
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData,int from){
        int minIndex = from;
        for(int k=from+1;k<quakeData.size();k++){
            if(quakeData.get(k).getDepth() > 
               quakeData.get(minIndex).getDepth()){
                   minIndex = k;
            }
        }
        return minIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for(int k=0;k<70;k++){
            int index = getLargestDepth(in,k);
            QuakeEntry qk = in.get(k);
            QuakeEntry qmin = in.get(index);
            in.set(k,qmin);
            in.set(index,qk);
        }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
    }
    
    private void onePassBubbleSort(ArrayList<QuakeEntry> quakeData,
                                  int numSorted){
        for(int k=0;k<quakeData.size()-numSorted;k++){
            QuakeEntry current = quakeData.get(k);
            QuakeEntry next = quakeData.get(k+1);
            if(current.getMagnitude() > next.getMagnitude()){
                quakeData.set(k,next);
                quakeData.set(k+1,current);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(int k=0;k<in.size();k++){
            /*for(QuakeEntry qe : in){
               System.out.println(qe);
            }*/
            onePassBubbleSort(in,k+1);
            //System.out.println("Printing quakes after pass " + k);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0; i<quakes.size() - 1; i++){
           if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                return false;
           }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck
                                                  (ArrayList<QuakeEntry> in){
        for(int k=0;k<in.size();k++){
            onePassBubbleSort(in,k+1);
            System.out.println("Printing quakes after pass " + (k+1));
            boolean check = checkInSortedOrder(in);
            if(check == true){
                break;
            }
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            System.out.println("Printing quakes after pass " + (i+1));
            boolean check = checkInSortedOrder(in);
            if(check == true){
                break;
            }
        }
    }
}