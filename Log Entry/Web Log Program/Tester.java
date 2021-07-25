
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile();
        analyzer.printAll();
    }
    
    public void testUniqueIp(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile();
        System.out.println(analyzer.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile();
        analyzer.printAllHigherThanNum(400);
    }
    
    public void testUniqueIpsOnDay(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       ArrayList<String> result = analyzer.uniqueIPVisitsOnDay("Sep 27");
       System.out.println(result.size());
    }
    
    public void testUniqueIpsInRange(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       System.out.println(analyzer.countUniqueIpsInRange(400,499));
    }
    
    public void testCountsVisitsperIP(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       HashMap<String,Integer> counts = analyzer.countVisitsPerIP();
       System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       HashMap<String,Integer> counts = analyzer.countVisitsPerIP();
       int maxNumber = analyzer.mostNumberVisitsByIP(counts);
       System.out.println(maxNumber);
    }
    
    public void testIPsMostVisits(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       HashMap<String,Integer> counts = analyzer.countVisitsPerIP();
       ArrayList<String> ip = analyzer.iPsMostVisits(counts);
       System.out.println(ip);
    }
    
    public void testIPsForDays(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       HashMap<String,ArrayList<String>> numbers = analyzer.iPsForDays();
       System.out.println(numbers);
    }
    
    public void testDayWithMostIPVisits(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       HashMap<String,ArrayList<String>> numbers = analyzer.iPsForDays();
       String date = analyzer.dayWithMostIPVisits(numbers);
       System.out.println(date);
    }
    
    public void testIPsWithMostVisitsOnDay(){
       LogAnalyzer analyzer = new LogAnalyzer();
       analyzer.readFile();
       HashMap<String,ArrayList<String>> numbers = analyzer.iPsForDays();
       ArrayList<String> result = analyzer.iPsWithMostVisitsOnDay(numbers,
                                                                 "Sep 29");
       System.out.println(result);
    }
}
