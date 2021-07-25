

public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDistance;
    private String name;
    public DistanceFilter(Location current,double max,String realName){
        loc = current;
        maxDistance = max;
        name = realName;
    }
    public boolean satisfies(QuakeEntry qe){
        return (qe.getLocation().distanceTo(loc) < maxDistance);
    }
    public String getName(){
        return name;
    }
}
