
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public void totalBirths (FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            int numberBirths = Integer.parseInt(record.get(2));
            totalBirths += numberBirths;
            if (record.get(1).equals("M")){
                totalBoys += numberBirths;
            }
            else {
                totalGirls += numberBirths;
            }
        }
        System.out.println("Total births: " + totalBirths);
        System.out.println("Total boys births: " + totalBoys);
        System.out.println("Total girls births: " + totalGirls);
    }
    public void testTotalBirths(){
        FileResource f = new FileResource();
        totalBirths(f);
    }
    public void totalNames(FileResource fr){
        int totalNames = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord record : fr.getCSVParser(false)){
            totalNames += 1;
            if (record.get(1).equals("M")){
                totalBoys += 1;
            }
            else {
                totalGirls += 1;
            }
        }
        System.out.println("Total names: " + totalNames);
        System.out.println("Total boys names: " + totalBoys);
        System.out.println("Total girls names: " + totalGirls);
    }
    public void testTotalNames(){
        FileResource fr = new FileResource();
        totalNames(fr);
    }
    public int getRank(int year,String name, String gender){
        FileResource fr = new FileResource();
        int rankBoys = 0;
        int rankGirls = 0;
        int ranking = 0;
        boolean NameNo = false;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(gender.contains("M") && record.get(1).contains("M")){
                rankBoys += 1;
            }
            if(gender.contains("F") && record.get(1).contains("F")){
                rankGirls += 1;
            }
            if(record.get(0).contains(name)){
                break;
            }
        }
        for(CSVRecord record : fr.getCSVParser(false)){
            if(record.get(0).contains(name)){
                NameNo = false;
                break;
            }
            else{
                NameNo = true;
            }
        }
        if(NameNo == false){
           if (gender.contains("M")){
              ranking = rankBoys;
           }
           if(gender.contains("F")){
              ranking = rankGirls;
           }
        }
        else{
            ranking = -1;
        }
        return ranking;
    } 
    public void testGetRank(){
        String gender = "M";
        String name = "Frank";
        int year = 1971;
        int rank = getRank(2012,name,gender);
        if(rank != -1){
           if(gender.contains("M")){
              System.out.println("The name " + name + " is  rank " + 
                                rank + " in year " + year + " for M");
           }
           else{
              System.out.println("Name " + name + " not found in gender M");
           }
           if(gender.contains("F")){
              System.out.println("The name " + name + " is  rank " + 
                                rank + " in year " + year + " for F");
           }
           else{
              System.out.println("Name " + name + " not found in gender F");
           }
        }
        else{
            System.out.println("The name " + name + " is not in this file");
        }
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        int rankBoys = 0;
        int rankGirls = 0;
        String name = "";
        CSVRecord line = null;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(gender.contains("M") == record.get(1).contains("M")){
                rankBoys += 1;
            }
            if(rank == rankBoys){
                line = record;
                break;
            }
        }
        for(CSVRecord record : fr.getCSVParser(false)){
            if(gender.contains("F") == record.get(1).contains("F")){
                rankGirls += 1;
            }
            if(rank == rankGirls){
                line = record;
                break;
            }
        }
        if(line == null){
           name = "NO NAME";
        }
        else{
           if(gender.contains("M")){
              name = line.get(0);
           }
           if(gender.contains("F")){
              name = line.get(0);
           }
        }
        return name;
    }
    public void testGetName(){
        int year = 2012;
        int rank = 4;
        String gender = "M";
        String name = getName(year, rank, gender);
        if(name.contains("NO NAME")){
            System.out.println("NO NAME");
        }
        else{
           if(gender.contains("M")){
              System.out.println("The name at rank " + rank + " in " + year
                                 + " is " + name);
           }
           if(gender.contains("F")){
              System.out.println("The name at rank " + rank + " in " + year
                                  + " is " + name);
           }
        }
    }
    public String whatIsNameInYear(String name,int year,
                                   int newYear, String gender){
       String newName = null;
       int rankName = getRank(year,name,gender);
       int rankNewName = getRank(newYear,name,gender);
       if (rankName == -1 || rankName == 0 || rankNewName == -1 || 
           rankNewName == 0){
           newName = "NO NAME";
       }
       if (gender.contains("M")){
          newName = getName(newYear,rankName,gender);
       }
       if (gender.contains("F")){
          newName = getName(newYear,rankName,gender);
       }
       return newName;
    }
    public void testWhatIsNameInYear(){
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        String newName = whatIsNameInYear(name,year,newYear,gender);
       if(newName == "NO NAME"){
           System.out.println("This name wasn't found in one of the files");
          }
        else{
           if(gender.contains("F")){
              System.out.println(name + " born in " + year + " would be " +
                              newName + " if she was born in " + newYear);
          }
           if(gender.contains("M")){
              System.out.println(name + " born in " + year + " would be " +
                                newName + " if she was born in " + newYear);
          }
       }
    }
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        int rankBoys = 0;
        int rankGirls = 0;
        int totalRankingB = 0;
        int totalRankingG = 0;
        int year = 0;
        String fileName = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            rankBoys = 0;
            rankGirls = 0;
            for (CSVRecord record : fr.getCSVParser(false)){
               if(gender.contains("M") && record.get(1).contains("M")){
                   rankBoys += 1;
               }
               if(gender.contains("F") && record.get(1).contains("F")){
                   rankGirls += 1;
               }
               if(record.get(0).contains(name)){
                   break;
               }
            }
            if(totalRankingB == 0 || totalRankingB>rankGirls){
                totalRankingB = rankBoys;
                fileName = f.getName();
            }
            if(totalRankingG == 0 || totalRankingG>rankGirls){
                totalRankingG = rankGirls;
                fileName = f.getName();
            }
        }
        fileName = fileName.substring(3,7);
        int fileYear = Integer.parseInt(fileName);
        if(gender.contains("M")){
            System.out.println("On year " + fileName + ", " + name + 
                           " was highest ranking, in position N°" + 
                            totalRankingB);
        }
        else{
            System.out.println("On year " + fileName + ", " + name + 
                           " was highest ranking, in position N°" + 
                            totalRankingG);
        }
        return fileYear;
    }
    public void testYearOfHighestRank(){
        String name = "Mich";
        String gender = "M";
        yearOfHighestRank(name,gender);
    }
    public double getAverageRank(String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        double totalRankingB = 0;
        double totalRankingG = 0;
        double fileNumbers = 0;
        double avgRank = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            fileNumbers += 1;
            for(CSVRecord record : fr.getCSVParser(false)){
                if(gender.contains("M") && record.get(1).contains("M")){
                   totalRankingB += 1;
               }
               if(gender.contains("F") == record.get(1).contains("F")){
                   totalRankingG += 1;
               }
               if(record.get(0).contains(name)){
                   break;
               }
            }
        }
                
        if(gender.contains("M")){
            avgRank = totalRankingB/fileNumbers;
        }
        if(gender.contains("F")){
            avgRank = totalRankingG/fileNumbers;
        }
        return avgRank;
    }
    public void testGetAverageRank(){
        String name = "Robert";
        String gender = "M";
        double avg = getAverageRank(name,gender);
        System.out.println("The average rank for " + name + " is " + avg);
    }
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr = new FileResource();
        int totalBirths = 0;
        for(CSVRecord record : fr.getCSVParser(false)){
            if(record.get(0).contains(name)){
                break;
            }
            if(gender.contains("M") && record.get(1).contains("M")){
               String births = record.get(2);
               int birthsN = Integer.parseInt(births);
               totalBirths += birthsN;
            }
            if(gender.contains("F") && record.get(1).contains("F")){
                String births = record.get(2);
                int birthsN = Integer.parseInt(births);
                totalBirths += birthsN;
            }
        }
        return totalBirths;
    }
    public void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int birthsHigher = getTotalBirthsRankedHigher(year,name,gender);
        if(gender.contains("M")){
            System.out.println("There are " + birthsHigher + 
                               " births ranked higher than " + name);
        }
        if(gender.contains("F")){
            System.out.println("There are " + birthsHigher + 
                               " births ranked higher than " + name);
        }
    }
}
