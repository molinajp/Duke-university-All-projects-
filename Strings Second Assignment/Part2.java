
public class Part2 {
    public int howMany(String stringa, String stringb){
        int total = 0;
        int firstap = stringb.indexOf(stringa);
        int nextap = stringb.indexOf(stringa,firstap + stringa.length());
        
        if (firstap == -1){
            return total;
        }
        else{
            total = 1;
            while (nextap != -1){
                total = total +1;
                nextap = stringb.indexOf (stringa,nextap 
                                           + stringa.length());
                                                           
                if (nextap == -1){
                   break;
                }
            }
            return total;
        }
       }
        public void testHowMany(){
        String a = "A";
        String b = "TAAAAGTAA";
        int howMany = howMany(a,b);
        System.out.println(howMany);
        
        a = "AG";
        b = "AGTA";
        howMany = howMany(a,b);
        System.out.println(howMany);
        
        a = "AGT";
        b = "TTA";
        howMany = howMany(a,b);
        System.out.println(howMany);
    }
}   



