import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldest = null;
        for (CSVRecord current : parser){
            if (coldest == null && current.get("TemperatureF") != "-9999"){
                coldest = current;
            }
            else {
                double currentTemperature = Double.parseDouble(current.get
                                                      ("TemperatureF"));
                double currentColdest = Double.parseDouble(coldest.get 
                                                      ("TemperatureF"));
                if (currentColdest>currentTemperature && 
                currentTemperature != -9999 && currentColdest != -9999){
                    coldest = current;
                }
            }
        }
        return coldest;
    }
    public void testColdestHourInFile (){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest temperature was " + 
                           coldest.get("TemperatureF") + " at "
                           + coldest.get("DateUTC"));
    }
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String file = null;
        CSVParser fileParser = null;
        CSVParser fileParser2 = null;
        FileResource finalFile = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentF = coldestHourInFile(fr.getCSVParser());
            if (coldest == null){
                file = f.getName();
                fileParser = fr.getCSVParser();
                fileParser2 = fr.getCSVParser();
                coldest = currentF;
            }
            double coldestTemperature = Double.parseDouble(coldest.get
                                                      ("TemperatureF"));
            double currentTemperature = Double.parseDouble(currentF.get 
                                                      ("TemperatureF"));                                          
            if (coldestTemperature>currentTemperature){
                file = f.getName();
                fileParser = fr.getCSVParser();
                fileParser2 = fr.getCSVParser();
                coldest = currentF;
            }
        }
        CSVRecord record = coldestHourInFile(fileParser);
        System.out.println("Coldest day was in file " + file);
        System.out.println("Coldest temperature on that day was " + 
                           record.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        for (CSVRecord temperature : fileParser2){
            String temp = temperature.get("TemperatureF");
            String date = temperature.get("DateUTC");
            System.out.println(date + " " + temp);
        }
        return file;
    }
    public void testFileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        String coldestFile = fileWithColdestTemperature();       
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowest = null;
        for (CSVRecord current : parser){
            if (lowest == null && current.get("Humidity") != "N/A"){
                lowest = current;
            }
            else{
                String LH = lowest.get("Humidity");
                String CH = current.get("Humidity");
                if (LH.equals("N/A") || CH.equals("N/A")){
                   System.out.println("N/A found");
                }
                else {
                   int lowestH = Integer.parseInt (lowest.get("Humidity"));
                   int currentH = Integer.parseInt (current.get("Humidity"));
                
                   if (lowestH>currentH){
                    lowest = current; 
                   }
                }
            }
        }
        System.out.println("The lowest humidity in file was " + 
                           lowest.get("Humidity") + " at " 
                           + lowest.get("DateUTC"));
        return lowest;
    }
    public void testLowestHumidityInFile (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidity = lowestHumidityInFile(parser);
    }
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        String file = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowest == null){
                lowest = current;
                file = f.getName();
            }
            int lowestH = Integer.parseInt(lowest.get("Humidity"));
            int currentH = Integer.parseInt(current.get("Humidity"));
            if (lowestH>currentH){
                lowest = current;
                file = f.getName();
            }
        }
        System.out.println ("The lowest humidity was in file " + file + 
                         " and it has a value of " + lowest.get("Humidity")
                         + " at " + lowest.get("DateUTC"));      
        return lowest;
    }
    public void testLowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestH = lowestHumidityInManyFiles();
    }
    public double averageTemperatureInFile (CSVParser parser){
        double avg = 0.0;
        int divBy = 0;
        double total = 0.0;
        for (CSVRecord record : parser){
            double N = Double.parseDouble(record.get("TemperatureF"));
            if(N != -9999){
               total = total + N;
               divBy = divBy + 1;
            }
        }
        avg = total / divBy;
        System.out.println("Average temperature in file is " + avg);
        return avg;
    }
    public void testAverageTemperatureInFile(){
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        averageTemperatureInFile(parser);        
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,
                                                           int value){
       double avg = 0.0;
       int divBy = 0;
       double totalT = 0.0;
       for (CSVRecord record : parser){
           int humidity = 0;
           if (record.get("Humidity").equals("N/A")){
              System.out.println ("N/A found");
           }
           else{
              humidity = Integer.parseInt(record.get("Humidity"));
                      
              if (humidity>=value){
                 divBy = divBy + 1;
                 double N = 0.0;
                 if(record.get("TemperatureF") != "-9999"){
                    N = Double.parseDouble(record.get("TemperatureF"));
                 }
                 totalT = totalT + N;
              }
           }
       }
       avg = totalT / divBy;
       if(divBy == 0){
           System.out.println("No temperatures with that humidity");
       }
       else{
           System.out.println("Average temperature when high humidity is " +
                             avg);
       }
       return avg;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        averageTemperatureWithHighHumidityInFile (parser, 80);
    }
}
