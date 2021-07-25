import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MagnitudeFilter(4.5,3.5,"Magnitude");
        Filter f2 = new DepthFilter(-20000,-55000,"Depth");
        /*Location loc = new Location(39.7392,-104.9903);
        double distance = 1000 * 1000;//los segundo mil son para sacar los km
        Filter f = new DistanceFilter(loc,distance,"Distance");
        Filter f2 = new PhraseFilter("end","a","Phrase");*/
        ArrayList<QuakeEntry> m7  = filter(list, f);
        ArrayList<QuakeEntry> m8 = filter(m7,f2);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        System.out.println("There are " + m8.size() + 
                           " quakes with those criteria");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(4.0,1.0,"Magnitude"));
        maf.addFilter(new DepthFilter(-30000,-180000,"Depth"));
        maf.addFilter(new PhraseFilter("any","o","Phrase"));
        ArrayList<QuakeEntry> finalList = filter(list,maf);       
        for (QuakeEntry qe : finalList){
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
        System.out.println("There are " + finalList.size() + 
                           " quakes with those criteria");
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(5.0,0.0,"Magnitude"));
        Location city = new Location(55.7308,9.1153);
        maf.addFilter(new DistanceFilter(city,3000*1000,"Distance"));
        maf.addFilter(new PhraseFilter("any","e","Phrase"));
        ArrayList<QuakeEntry> finalList = filter(list,maf);
        for(QuakeEntry qe : finalList){
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
        System.out.println("There are " + finalList.size() + 
                           " quakes with those criteria");
    }
}
