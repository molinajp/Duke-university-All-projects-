
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile() {
         FileResource fr = new FileResource();
         for(String line : fr.lines()){
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry log : records){
             String ip = log.getIpAddress();
             if(! unique.contains(ip)){
                 unique.add(ip);
             }
         }
         return unique.size();
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         ArrayList<LogEntry> higher = new ArrayList<LogEntry>();
         for(LogEntry log : records){
             int statusCode = log.getStatusCode();
             if(statusCode>num){
                 higher.add(log);
             }
         }
         for(LogEntry log : higher){
             System.out.println("The log:");
             System.out.println(log);
             System.out.println("has a status code higher than " + num);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> visits = new ArrayList<String>();
         for(LogEntry log : records){
             Date d = log.getAccessTime();
             String date = d.toString();
             String ip = log.getIpAddress();
             if(date.contains(someday) && ! visits.contains(ip)){
                 visits.add(log.getIpAddress());
             }
         }
         return visits;
     }
     
     public int countUniqueIpsInRange(int low,int high){
         ArrayList<String> array = new ArrayList<String>();
         for(LogEntry log : records){
             int statusCode = log.getStatusCode();
             String ip = log.getIpAddress();
             if(statusCode>=low && statusCode<=high && ! array.contains(ip)){
                array.add(ip);
             }
         }
         return array.size();
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(LogEntry log : records){
             String ip = log.getIpAddress();
             if(! counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         int maxVisits = 0;
         for(String s : counts.keySet()){
             int current = counts.get(s);
             if(current>maxVisits){
                 maxVisits = current;
             }
         }
         return maxVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> IPs = new ArrayList<String>();
         int number = mostNumberVisitsByIP(counts);
         for(String s : counts.keySet()){
             if(counts.get(s).equals(number)){
                 IPs.add(s);
             }
         }
         return IPs;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> numbers = 
         new HashMap<String,ArrayList<String>>();
         for(LogEntry log : records){
             Date d = log.getAccessTime();
             String d2 = d.toString();
             String date = d2.substring(4,10);
             String ip = log.getIpAddress();
             if(! numbers.containsKey(date)){
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(ip);
                 numbers.put(date,ips);
             }
             else{
                 ArrayList<String> ips = numbers.get(date);
                 ips.add(ip);
                 numbers.put(date,ips);                 
             }
         }
         return numbers;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> n){
         String result = null;
         int max = 0;
         for(String s : n.keySet()){
             ArrayList<String> sArray = n.get(s);
             int current = sArray.size();
             if(current>max){
                 max = current;
                 result = s;
             }
         }
         return result;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay
                             (HashMap<String,ArrayList<String>> n,
                             String date){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        ArrayList<String> visitsOnDay = n.get(date);
        for(String ip : visitsOnDay){
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip) + 1);
            }
        }
        return iPsMostVisits(counts);
     }
}
