
public class Part2 {
    public String findSimpleGene (String dna,int startIndex,int endIndex){
        String result="";
        startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        endIndex = dna.indexOf ("TAA",startIndex+3);
        if (endIndex == -1){
            return "";
        }
        result= dna.substring(startIndex,endIndex+3);
        int mod3 = result.length()%3; 
        if(mod3 == 0) {
            if (dna == dna.toUpperCase()){
                result = result.toUpperCase();                            
            }
            if (dna == dna.toLowerCase()){
                result = result.toLowerCase();            
            }
            return result;
        }
        else {
            return "";
        }
    }
    public void testSimpleGene (){
        String dna = "AAGTTGGGAATAAGTA";
        System.out.println ("dna is " + dna);
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf ("TAA",startIndex+3);
        String gene = findSimpleGene (dna,startIndex,endIndex);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
        dna = "AAATGGTGTAGTGA";
        System.out.println ("dna is " + dna);
        gene = findSimpleGene (dna,startIndex,endIndex);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
        dna = "atggtaggttgattatgataa";
        System.out.println ("dna is " + dna);
        gene = findSimpleGene (dna,startIndex,endIndex);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println ("dna is " + dna);
        gene = findSimpleGene (dna,startIndex,endIndex);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
    }
}
