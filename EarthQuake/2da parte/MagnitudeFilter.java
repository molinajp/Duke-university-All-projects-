

public class MagnitudeFilter implements Filter{
    private double maxMagnitude;
    private double minMagnitude;
    private String name;
    public MagnitudeFilter(double max,double min,String realName){
        maxMagnitude = max;
        minMagnitude = min;
        name = realName;
    }
    public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude() <= maxMagnitude && 
                qe.getMagnitude() >= minMagnitude);
    }
    public String getName(){
         return name;
    }
}
