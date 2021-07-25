import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude
                             (ArrayList<QuakeEntry> quakeData,double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom
              (ArrayList<QuakeEntry> quakeData,double distMax,Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
            if(loc.distanceTo(from)/1000 < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                              double minDepth,double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            double current = qe.getDepth();
            if(current>minDepth && current<maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                String where,String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if(where.equals("start")){
                if(title.startsWith(phrase)){
                   answer.add(qe);
                }
            }
            if(where.equals("end")){
                if(title.endsWith(phrase)){
                   answer.add(qe);
                }
            }
            if(where.equals("any")){
                if(title.contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        System.out.println("There were " + listBig.size() + " quakes:");
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read
        ("data/nov20quakedatasmall.atom");
        System.out.println("read data for "+list.size()+" quakes");
        // This location is Durham, NC
        /*Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> distanceList = 
        filterByDistanceFrom(list,1000,city);
        for(QuakeEntry qe : distanceList){
            Location loc = qe.getLocation();
            System.out.println(loc.distanceTo(city) + qe.getInfo());
        }
        System.out.println("Found " + distanceList.size() + 
                           " quakes that match that criteria");
                           */
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> distanceList = 
        filterByDistanceFrom(list,1000,city);
        for(QuakeEntry qe : distanceList){
            Location loc = qe.getLocation();
            System.out.println(loc.distanceTo(city) + " " + qe.getInfo());
        }
        System.out.println("Found " + distanceList.size() + 
                           " quakes that match that criteria");
        
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read
        ("data/nov20quakedata.atom");
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> depthList = filterByDepth(list,-4000,-2000);
        for(QuakeEntry qe : depthList){
            System.out.println(qe);
        }
        System.out.println("Found " + depthList.size() + 
                           " that match that criteria");
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read
        ("data/nov20quakedata.atom");
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> phraseList = filterByPhrase(list,
                                                 "any","Can");
        for(QuakeEntry qe : phraseList){
            System.out.println(qe);
        }
        System.out.println("Found " + phraseList.size() + 
                           " that match that criteria");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
