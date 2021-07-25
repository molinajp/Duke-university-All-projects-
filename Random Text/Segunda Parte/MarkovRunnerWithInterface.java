
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(AbstractMarkovModel markov, String text,
                                int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
        
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 48);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 48);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 48);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 48);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void testHashMap(){
        EfficientMarkovModel markov = new EfficientMarkovModel(5);
        int seed = 615;
        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n',' ');
        //String text = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        runModel(markov,text,50,531);
    }
    
    public void compareMethods(){
        MarkovModel markov = new MarkovModel(2);
        EfficientMarkovModel markov1 = new EfficientMarkovModel(2);
        int seed = 42;
        int size = 1000;
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        runModel(markov,st,size,seed);
        System.out.println(System.nanoTime());
        runModel(markov1,st,size,seed);
        System.out.println(System.nanoTime());
    }
}
