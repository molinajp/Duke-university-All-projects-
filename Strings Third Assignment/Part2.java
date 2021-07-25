
import edu.duke.*;
import java.io.File;

public class Part2 {
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
    
    public StorageResource getAllGenes (String dna){
        StorageResource allGenes = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        String firstGene = findGene(dna);
        if (firstGene == ""){
            return allGenes;
        }
        else{
            allGenes.add(firstGene);
            int nextIndex = dna.indexOf("ATG",startIndex+1);
            String nextGene = findGene(dna.substring(nextIndex));
            while (nextGene != ""){
                allGenes.add(nextGene);
                nextIndex = dna.indexOf("ATG",nextIndex+1);
                if (nextIndex == -1) {
                    return allGenes;
                }
                else {
                    nextGene = findGene(dna.substring(nextIndex));
                }
            }
        }
        return allGenes;
    }
    public double cgRatio(String dna){
        int totalC = 0; 
        int firstC = dna.indexOf("C");
        if (firstC != -1){
            totalC = 1;
        }
        int nextC = dna.indexOf ("C",firstC+1);
        while (nextC != -1){
            totalC = totalC + 1;
            nextC = dna.indexOf ("C",nextC+1);
        }
        
        int totalG = 0;
        int firstG = dna.indexOf("G");
        if (firstG != -1){
            totalG = 1;
        }
        int nextG = dna.indexOf ("G",firstG+1);
        while (nextG != -1){
            totalG = totalG + 1;
            nextG = dna.indexOf ("G",nextG+1);
        }
        int length = dna.length();
        int totalGC = totalG + totalC;
        double ratio = (double)totalGC / length; 
        return ratio;
    }
    public int countCTG (String dna){
        int total = 0;
        int firstCTG = dna.indexOf("CTG");
        if (firstCTG == -1){
            return total;
        }
        else {
            total = 1;
        }
        int nextCTG = dna.indexOf("CTG",firstCTG + 1);
        while (nextCTG != -1){
            total = total + 1;
            nextCTG = dna.indexOf("CTG",nextCTG + 1);
        }
        return total;
    }
    public void testGetAllGenes(){
        String dna = "AATGFCSCCGTAASRATGTAGATGTGA";
        System.out.println("Getting all genes in " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String s : genes.data()){
            System.out.println(s);
        }
    }
    public void testcgRatio(){
        String dna = "GGGCCCAAAAAA";
        double ratio = cgRatio(dna);
        System.out.println(ratio);
    }
    public void testcountCTG(){
        String dna = "";
        int CTGs = countCTG(dna);
        System.out.println(CTGs);
    }
    public void processGenes (StorageResource sr){
       for (String s : sr.data()){
          String firstGene = findGene(s);
          int totalGenes = 0;
          if (firstGene != ""){
              totalGenes = 1;
            }
          int longest = firstGene.length(); 
          if (s == ""){
             System.out.println("No gene");
          }
            
          if(firstGene.length()>60){
                System.out.println("Gene longer than 60: " + firstGene);
          }
          if(cgRatio(firstGene)>0.35){
             System.out.println("Gene cgRatio more than 0.35: " 
                                   + firstGene);
          }
          int indexFirstGene = s.indexOf("ATG");
          String nextGene = findGene(s.substring(indexFirstGene + 1));
          int indexNextGene = s.indexOf("ATG",firstGene.length() 
                                          + indexFirstGene);
          while (nextGene != ""){
             totalGenes = totalGenes + 1;
             nextGene = findGene(s.substring(indexNextGene + 1));
             indexNextGene = s.indexOf("ATG", nextGene.length()
                                              + indexNextGene);
             if(nextGene.length()>60){
                System.out.println("Gene longer than 60: " + nextGene);
             }
             if(cgRatio(nextGene)>0.35){
                System.out.println("Gene cgRatio more than 0.35: " 
                                   + nextGene);
             }
             if (longest<nextGene.length()){
                longest = nextGene.length();
             }
          }
          System.out.println("Longest Gene has " +  longest + " letters");
          System.out.println("Total genes: " + totalGenes);
       } 
    }
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        String dna1 = "";
        
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        sr.add(dna1);
        processGenes(sr);
    }
}
