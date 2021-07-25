

public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    private String name;
    public DepthFilter(double max,double min,String realName){
        minDepth = min;
        maxDepth = max;
        name = realName;
    }
    public boolean satisfies(QuakeEntry qe){
        return (qe.getDepth() <= maxDepth && qe.getDepth() >= minDepth);
    }
    public String getName(){
        return name;
    }
}
