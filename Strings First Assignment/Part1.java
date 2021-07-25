
public class Part1 {
    public String findSimpleGene (String dna){
        String result="";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int endIndex = dna.indexOf ("TAA",startIndex+3);
        if (endIndex == -1){
            return "";
        }
        result= dna.substring(startIndex,endIndex+3);
        int mod3 = result.length()%3; 
        if(mod3 == 0) {
            return result;
        }
        else {
            return "";
        }
    }
    public void testSimpleGene (){
        String dna = "AAGTTGGGAATAAGTA";
        System.out.println ("dna is " + dna);
        String gene = findSimpleGene (dna);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
        dna = "AAATGGTGTAGTGA";
        System.out.println ("dna is " + dna);
        gene = findSimpleGene (dna);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
        dna = "ATGGTAGGTTGATTATGATAA";
        System.out.println ("dna is " + dna);
        gene = findSimpleGene (dna);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println ("dna is " + dna);
        gene = findSimpleGene (dna);
        if (dna != null) {
            System.out.println ("Gene is " + gene);        
        }
        else {
            System.out.println ("Gene is ");
        }
        
    }
}
                             