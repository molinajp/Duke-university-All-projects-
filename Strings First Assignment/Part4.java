import edu.duke.*;
import java.io.File;
public class Part4 {
    public String findYoutube(String ur){
        int youtube = 0;
        if (ur != ""){
             youtube = ur.indexOf ("youtube.com");     
             ur = ur.toLowerCase();
        if (youtube != -1) {
            int first = ur.indexOf ("/", youtube+1);
            int second = ur.lastIndexOf ("/", youtube);
            String link = ur.substring (0,second-1)+ 
            ur.substring(first,ur.length());
            return link;
        }
        else {
            return "No Youtube";
        }
        }
        else {
            return "No Youtube";
        }
    }
    public void test (){
     String url = 
     "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
     URLResource ur = new URLResource(url);
     StorageResource sr = new StorageResource ();
     for (String words: ur.lines()){
         
         String youtube = findYoutube(words);
         System.out.println(youtube);
        }
    }
}
