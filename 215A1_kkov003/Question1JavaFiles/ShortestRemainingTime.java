import java.util.ArrayList;

/* CS215 Assignment One 2013
 * Code completed by kkov003
 * 
 * Ksenia Kovaleva
 * Id: 4716583
 * 
 * ShortestRemainingTime class
 * Pre-emptive job scheduling algorithm called in Question1
 * 
 * */

public class ShortestRemainingTime extends AllocationStrategy {
  
  public ShortestRemainingTime(ArrayList<Job> jobs) {
    super(jobs);
    
  }
  
  @Override
  public void run() {
    int sysTime;
    int i; // pointer to next Job to submit
    int numJobs;
    Queue = new ArrayList<Job>();
    
    if(!Jobs.isEmpty()){
      
      Job firstJob = Jobs.get(0);
      
      int start = firstJob.getSubmitTime(); 
      sysTime = start; //system time starts at first submit
      
      Queue.add(firstJob);
      i=1;
      numJobs = 1;
      
      while(!Queue.isEmpty()){
        
        //If process list has more than one job finds shortest job
        if(Queue.size() > 1){
          
          int j = 0; // top of Queue array list
          for(int k=1; k< Queue.size(); k++){
            if(Queue.get(k).getCPUTimeLeft() < Queue.get(j).getCPUTimeLeft()){
              j=k;//index of Job with smallest remaining time
            } // end if
          }//end for
          Job shortestTimeLeft = Queue.remove(j);
          Queue.add(0,shortestTimeLeft); // puts shortest job as next to be processed
        } // end Queue is >1 condition
        
        Job currentJ = Queue.get(0);// point to top of Queue array list
        
        if(currentJ.getCPUTimeLeft() == currentJ.getCPUTime()){ // current job has not started
          //determines start time
          
          if(currentJ.getSubmitTime() > sysTime){
            start = currentJ.getSubmitTime();// rogue case of a job being submitted after previous jobs have finished
            sysTime = start; // if submit time is greater sysTime updated
          }else{
            start = sysTime;
          } // end else
          
          currentJ.start(start);
        } //end if of start time condition
        
        sysTime++;
        currentJ.tick(sysTime); // TICK
        if(currentJ.getCPUTimeLeft() == 0){
          Queue.remove(0); // removes finished job
        }
        
        if(i< Jobs.size()){ // i is in range of Jobs arraylist index
          
          Job nextSubmit = Jobs.get(i); // i is already pointing to 2nd job in jobs array list
          int subTime = nextSubmit.getSubmitTime();
          
          while(subTime <= sysTime && i<Jobs.size()){
            
            Queue.add(nextSubmit);
            numJobs++;
            i++; // look at next one
            
            if(i< Jobs.size()){
              nextSubmit = Jobs.get(i);
              subTime = nextSubmit.getSubmitTime();
            } 
            
          }// end while subTime
        }
        if(Queue.isEmpty() && numJobs < Jobs.size()){
          
          Queue.add(Jobs.get(i));
          numJobs++;
          i++;
        } //close if
        
      } // end while loop
      
    }else{// end if
      System.out.println("The input file is empty. There are no jobs to process.");
    } // end if there is something in Jobs array list
    
  } // ends run method
  
} // ends SRT class
