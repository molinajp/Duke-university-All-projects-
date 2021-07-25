
import java.io.*;
import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void simulate(int rolls){
        Random rand = new Random();
        int[] count = new int[13];
        for(int k=0;k<rolls; k++){
            int d1 = rand.nextInt(6)+1;
            int d2 = rand.nextInt(6)+1;
            count[d1+d2] += 1;
        }
        for(int k=2;k<=12;k++){
            System.out.println(k + "'s=\t" + count[k] + "\t" + 
                               100.0*count[k]/rolls);
        }
    }

}
