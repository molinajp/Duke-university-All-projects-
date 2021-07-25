import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String Nauru = countryInfo(parser,"Nauru");
        System.out.println(Nauru);
        
        parser = fr.getCSVParser();
        
        listExportersTwoProducts (parser,"gold","diamonds");
        
        parser = fr.getCSVParser();
        System.out.println("ACAAAAAAAAAAAAAAAAA");
        int numberExporters = numberOfExporters(parser,"cocoa");
        
        parser = fr.getCSVParser();
        bigExporters (parser,"$999,999,999,999");
    }
    public String countryInfo(CSVParser parser, String country){
        String last = "";
        for (CSVRecord record : parser){
            String countries = record.get("Country");
            if (countries.contains(country)){
               String exports = record.get("Exports"); 
               String dollars = record.get("Value (dollars)");
               last = country + ": " + exports + ": " + dollars;
               return last;
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser,String export1,
                                                  String export2){
       for (CSVRecord record : parser){
          String items = record.get("Exports");
          if (items.contains(export1) && items.contains(export2)){
             String country = record.get ("Country");
             System.out.println(country);
          }
       }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int total = 0;
        for (CSVRecord record : parser){
            String item = record.get("Exports");
            if(item.contains(exportItem)){
                total = total + 1;
            }
        }
        System.out.println(total);
        return total;
    }
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (amount.length()< value.length()){
                System.out.println(country + ": " + value);
            }
        }
    }
}


