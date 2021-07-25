
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetsFollows(){
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("t.");
        System.out.println(follows);
        System.out.println(follows.size());
    }
    public void testGetFollowWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println(follows);
        System.out.println(follows.size());
    }
}
