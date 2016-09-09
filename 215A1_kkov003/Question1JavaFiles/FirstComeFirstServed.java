import java.util.ArrayList;

/* CS215 Assignment One 2013
 * Code completed by kkov003
 * 
 * Ksenia Kovaleva
 * Id: 4716583
 * 
 * FirstComeFirstServed class
 * Non-preemptive job scheduling algorithm called in Question1
 * */


public class FirstComeFirstServed extends AllocationStrategy { 
  
  public FirstComeFirstServed(ArrayList<Job> jobs) { 
    super(jobs);
  } //end constructor
  
  @Override
  public void run() { // start run
    
    if(!Jobs.isEmpty()){
      int i = 0;
      int start = Jobs.get(0).getSubmitTime();//first job will start on submit time
      int sysTime = start;//systime starts at first submission
      
      
      
      while(i< Jobs.size()){ // start while
        
        
        Job current = Jobs.get(i); // looks at job from job list
        
        int submitTime = current.getSubmitTime();
        
        
        if(submitTime > sysTime){ // if a job submit time is greater than finish time of last job
          start = submitTime;
          sysTime = start;
        }else{ // submitTime is smaller or equal to sysTime
          start = sysTime;
        }
        
        
        current.start(start); //sets start time
        
        int totalTime = current.getCPUTime();
        current.setCPUTimeLeft(totalTime);
        
        while(current.getCPUTimeLeft()>0){ // keep running until process done
          
          sysTime++;//every tick time increases 
          current.tick(sysTime); //decrements cpu time 
          //once job done tick sets end time
          
        } 
        
        i++;
        
      }// end while loop
    }else{// end if
      System.out.println("The input file is empty. There are no jobs to process.");
    }
    
    
  } // end run
  
} // class ends
