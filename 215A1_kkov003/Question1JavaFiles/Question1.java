import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Application class for Assignment 1, Question 1, compsci215 2013
 * 
 * @author dber021
 * completed by kkov003
 * Name:Ksenia Kovaleva
 * Id:4716583
 */
public class Question1 {
  
  public static void main(String[] args) {
    
    String inputFile = args[0];
    String algType = args[1];
    int timeQuant = 0;
    ArrayList<Job> jobs = new ArrayList<Job>();

    Scanner scan = null;
    
    try { // read the file
      scan = new Scanner(new File(inputFile));
      
      scan.useDelimiter(",|\\s");
      while (scan.hasNext()){
        
        
        int p_ID = scan.nextInt();
        int submitTime = scan.nextInt();
        int cpuTime = scan.nextInt();
        
        
        jobs.add(new Job(p_ID, submitTime, cpuTime));
      }
      scan.close();
    }
    catch (IOException e){
      System.out.println("IOException caught");
    }
    
    if(algType.equals("FCFS")){
      
      FirstComeFirstServed fcfs = new FirstComeFirstServed(jobs);
      fcfs.run();
      if(!jobs.isEmpty()){ // don't print table for empty file
        printJobs(jobs);
      }
      
    }else if(algType.equals("SRT")){
      ShortestRemainingTime srt = new ShortestRemainingTime(jobs);
      srt.run();
      if(!jobs.isEmpty()){ 
        printJobs(jobs);
      }
      
    }else if(algType.equals("RR")){
      
      if(args.length == 3){ // only run if you have enough args passed down
        try{ 
          timeQuant = Integer.parseInt(args[2]);  
          RoundRobin rr = new RoundRobin(jobs,timeQuant); 
          rr.run();
          if(!jobs.isEmpty()){ 
            printJobs(jobs);
          }
        }// end try
        catch (NumberFormatException e){
          System.out.println("Please enter an integer for the time quantum.");
        }// end catch
        
      }else{
        System.out.println("Please specify a time quantum to run Round Robin.");
      }//end else
      
    }
    
    jobs.clear();//clear array list for next execution
    
  } //closes main method
  
  private static void printJobs(ArrayList<Job> jobs){ // methods prints job statistics
    int turnaroundT;
    int waitingT;
    int processId;
    
    double aveTurn;
    double totalTurn =0;
    double totalWait =0;
    double aveWait;
    int i =0;
    
    System.out.println("=============================================");
    System.out.printf("%-12s|%-17s|%-12s\n","Process ID","Turnaround time","Waiting Time");       
    System.out.println("=============================================");
    
    while(i< jobs.size()){
      Job j = jobs.get(i);
      
      processId = j.getId();
      turnaroundT = (j.getEndTime())-(j.getSubmitTime());
      waitingT = turnaroundT - j.getCPUTime();
      
      System.out.printf("%-12d|%-17d|%-12d\n",processId,turnaroundT,waitingT);
      System.out.println("---------------------------------------------");
      
      totalTurn +=turnaroundT;
      int waitTime = turnaroundT - j.getCPUTime();
      totalWait += waitTime;
      
      i++;
    } // end while
    
    aveTurn = totalTurn / (jobs.size());
    aveWait = totalWait / (jobs.size());
    
    System.out.println("=============================================");
    System.out.printf("%-12s|%-17.2f|%-12.2f\n","Avg.",aveTurn,aveWait);
    System.out.println("=============================================");
    
    
    
    
  }// closes printJobs method
  
} // closes question 1 class
