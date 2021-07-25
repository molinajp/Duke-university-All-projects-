
public class Part3 {
    public Boolean twoOccurences (String stringa, String stringb){
        int appeareance1 = stringb.indexOf(stringa);
        int appeareance2 = stringb.indexOf(stringa, appeareance1 + 
        stringa.length());
        Boolean result = null;
        if (appeareance1 == -1){
            result = false;
        }
        if (appeareance2 == -1){
            result = false;
        }
        else {
            result = true;
                }
        return result;
            }
    public String lastPart (String stringa, String stringb){
        int appeareance1 = stringb.indexOf(stringa);
        String result = stringb.substring(appeareance1 + stringa.length());
        if (appeareance1 == -1){
            return stringb;
        }
        else {
            return result;
        }
    }        
    public void testing(){
        String a = "by";
        String b = "A story by Abby Long";
        Boolean result = twoOccurences (a,b);
        String result2 = lastPart (a,b);
        System.out.println (result);
        System.out.println (result2);
        
        a = "a";
        b = "aloha";
        result = twoOccurences (a,b);
        result2 = lastPart (a,b);
        System.out.println (result);
        System.out.println (result2);
        
        a = "atg";
        b = "ageadgafgdsfg";
        result = twoOccurences (a,b);
        result2 = lastPart (a,b);
        System.out.println (result);
        System.out.println (result2);
        }
    
}
    
    