
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1,QuakeEntry q2){
        String stringQ1 = q1.getInfo();
        String stringQ2 = q2.getInfo();
        String answerq1 = stringQ1.substring(stringQ1.lastIndexOf(" ") + 1);
        String answerq2 = stringQ2.substring(stringQ2.lastIndexOf(" ") + 1);
        int number = answerq1.compareTo(answerq2);
        if(number != 0){
            return number;
        }
        else{
            return Double.compare(q1.getMagnitude(),q2.getMagnitude());
        }
    }
}
