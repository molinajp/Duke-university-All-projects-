
public class Part1 {
    public  int findStopCodon (String dna, 
                               int startIndex,
                               String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        while (stopIndex != -1){
            if ((startIndex-stopIndex)%3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex+1);
            }
        }
        return dna.length();
    }
    public void testFindStopCodon (){
        String dna = "AGAATGTAA";
        int StopCodonIndex = findStopCodon(dna,0,"TAA");
        System.out.println(StopCodonIndex);
        
        dna = "AGAATGGTAA";
        StopCodonIndex = findStopCodon(dna,0,"TAA");
        System.out.println(StopCodonIndex);
    }
    public String findGene (String dna){
        int startIndex = dna.indexOf("ATG");;
        if (startIndex == -1){
            return "";
        }
        int stopCodonIndexTAA = findStopCodon(dna,startIndex,"TAA");
        int stopCodonIndexTAG = findStopCodon(dna,startIndex,"TAG");
        int stopCodonIndexTGA = findStopCodon(dna,startIndex,"TGA");
        int minIndex = Math.min (stopCodonIndexTAA,Math.min 
                                (stopCodonIndexTAG,stopCodonIndexTGA));
        if (minIndex == dna.length()){
            return "";
        }
        else {
            return dna.substring(startIndex,minIndex+3);
        }
    }
    public String printGenes(String dna){
        int total = 0;
        int startIndex = dna.indexOf("ATG");
        String firstGene = findGene(dna);
        if (firstGene == ""){
            return "There is no gene.";
        }
        else{
            int nextIndex = dna.indexOf("ATG",startIndex+1);
            String nextGene = findGene(dna.substring(nextIndex));
            while (nextGene != ""){
                System.out.println("Next Gene is " + nextGene);
                nextIndex = dna.indexOf("ATG",nextIndex+1);
                if (nextIndex == -1) {
                    return "";
                }
                else {
                    nextGene = findGene(dna.substring(nextIndex));
                }
            }
        }
        return "";
    }
    public void testFindGene (){
        String dna = "AAAGGGTTTTAA";
        System.out.println(dna);
        String gene = findGene(dna);
        if (gene != ""){
            System.out.println("Gene is " + gene);
        }
        else{
            System.out.println("Gene is " + gene);
        }
        
        dna = "AATGTGGTATTAAAFDATGTAAATGTAG";
        System.out.println(dna);
        gene = findGene(dna);
        if (gene != ""){
            System.out.println("Gene is " + gene);
        }
        else{
            System.out.println("Gene is " + gene);
        }
        String allGenes = printGenes(dna);
        System.out.println(allGenes);
        
        dna = "AATGGGTTAGTAA";
        System.out.println(dna);
        gene = findGene(dna);
        if (gene != ""){
            System.out.println("Gene is " + gene);
        }
        else{
            System.out.println("Gene is " + gene);
        }
        
        dna = "AATGGGTTGTTAT";
        System.out.println(dna);
        gene = findGene(dna);
        if (gene != ""){
            System.out.println("Gene is " + gene);
        }
        else{
            System.out.println("Gene is " + gene);
        }
        
        dna = "AATGGGTTGTAA";
        System.out.println(dna);
        gene = findGene(dna);
        if (gene != ""){
            System.out.println("Gene is " + gene);
        }
        else{
            System.out.println("Gene is " + gene);
        }
    }
}
