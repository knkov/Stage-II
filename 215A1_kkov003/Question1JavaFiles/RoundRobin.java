import java.util.ArrayList;

/* CS215 Assignment One 2013
 * Code completed by kkov003
 * 
 * Ksenia Kovaleva
 * Id: 4716583
 * 
 * RoundRobin class 
 * Pre-emptive job scheduling algorithm called in Question1
 * 
 * This Round Robin switches jobs before adding a new one
 * if the switch and new job submission are occuring simultaneously.
 * 
 * */

public class RoundRobin extends AllocationStrategy {
  
  int timeQuant;
  
  public RoundRobin(ArrayList<Job> jobs, int timeSlice) {
    super(jobs);
    timeQuant = timeSlice;
    Queue = new ArrayList<Job>();
  } // end constructor
  
  @Override
  public void run() { 
    
    int i; // pointer in Jobs array list
    int sysTime;
    boolean jobFinished;
    int t; // stand in time slice
    int switchTime;
    int numJobs;//tracks num of jobs going into process queue
    
    
    if (!Jobs.isEmpty()){ // at least one job
      
      
      Job firstJob = Jobs.get(0);
      
      int start = firstJob.getSubmitTime(); // 
      sysTime = start; //system time starts at first submit
      
      Queue.add(firstJob); // added to process queue
      numJobs=1;
      i = 1; // pointer for Jobs array list
      
      while(!Queue.isEmpty()){
        
        Job currentQ = Queue.get(0);// point to top
        
        if(currentQ.getCPUTimeLeft() == currentQ.getCPUTime()){ // current job has not started
          // set start time
          if(currentQ.getSubmitTime() > sysTime){
            start = currentQ.getSubmitTime();// case of a job arriving after preceding jobs are finished
            sysTime = start; // if submit time is greater then systime needs to be updated
          }else{
            start = sysTime;
          } // end else
          
          currentQ.start(start);
        } //ends set start time conditions
        
        if(currentQ.getCPUTimeLeft() > timeQuant){ // job won't finish in this time quantum
          jobFinished = false;
          t = timeQuant;
        }else{ // time left smaller or equal to time slice
          
          t = currentQ.getCPUTimeLeft();
          jobFinished = true;
        } // end time slice if
        
        switchTime = sysTime + t; // time of switch
        
        int j;
        Job switchEqualJob = null;
        for(j=1; j <= t; j++){ // tick t amount of times
          
          sysTime++; // increases current time by 1
          currentQ.tick(sysTime); // decreases CPU time left by 1 and sets end time if applicable
          
          if( i< Jobs.size()){ // 'i' won't go outside job array list range
            
            Job nextSubmit = Jobs.get(i); // i starts off pointing to 2nd job
            int subTime = nextSubmit.getSubmitTime();
            
            while(subTime <= sysTime && i< Jobs.size()){ 
              //will add jobs with submit time the same as current job 
              //and jobs with submit time equal to current Time (sysTime)
              
              numJobs++;
              i++;
              
              if(subTime == switchTime){ // same as switch time
                switchEqualJob = nextSubmit;
              }else {
                Queue.add(nextSubmit); // adds job that comes in during time slice
                
                
              } // end else
              
              if(i<Jobs.size()){
                nextSubmit = Jobs.get(i);
                subTime = nextSubmit.getSubmitTime(); // test condition
              } 
              
            } // end if new jobs exist to add
          } // end if for 'i'
          
          
        } // ends tick
        
        
        //---SWITCH----
        if(jobFinished){
          Queue.remove(0); //take out of processing queue
        }else{ // job not finished
          
          Queue.add(currentQ); // add current job to end
          Queue.remove(0); // remove job from top
          
        } //end if 
        
        //----Add Job Coming in at Same time as Switch ---  
        
        if(switchEqualJob != null){ // if there is a switch equal job
          Queue.add(switchEqualJob);
        }
        
        if(Queue.isEmpty() && numJobs < Jobs.size()){// if there are any jobs unprocessed from Jobs queue process them
          Queue.add(Jobs.get(i));
          numJobs++;
          i++;
        } //close if
        
      }// end while
      
      
    }else{
      System.out.println("The input file is empty. There are no jobs to process.");// end first if
    }
  } // closes run
  
} // closes class

