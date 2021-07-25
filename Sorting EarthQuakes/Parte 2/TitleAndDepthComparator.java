
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1,QuakeEntry q2){
        int title = q1.getInfo().compareTo(q2.getInfo());
        if(title != 0){
            return title;
        }
        return (Double.compare(q1.getDepth(),q2.getDepth()));
    }
}
