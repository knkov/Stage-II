/* CS215 Assignment One 2013
 * Name:Ksenia Kovaleva
 * Id:4716583
 * upi:kkov003
 */

public class Job {
  private int id, submitTime, CPUTime, CPUTimeLeft;
  
  private int startTime = 0, endTime = 0;
  
  
  
  public Job(int id, int submitTime, int CPUTime) {
    super();
    this.id = id;
    this.submitTime = submitTime;
    this.CPUTime = CPUTime;
    this.CPUTimeLeft = CPUTime;
    
  }
  
  public void start(int sysTime) {
    startTime = sysTime;
  }
  
  public void tick(int sysTime) {
    CPUTimeLeft --;
    if (CPUTimeLeft <= 0){
      endTime = sysTime;
      
    }
    
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getSubmitTime() {
    return submitTime;
  }
  
  public void setSubmitTime(int submitTime) {
    this.submitTime = submitTime;
  }
  
  public int getCPUTime() {
    return CPUTime;
  }
  
  public void setCPUTime(int cPUTime) {
    CPUTime = cPUTime;
  }
  
  public int getCPUTimeLeft() {
    return CPUTimeLeft;
  }
  
  public void setCPUTimeLeft(int cPUTimeLeft) {
    CPUTimeLeft = cPUTimeLeft;
  }
  
  public int getStartTime() {
    return startTime;
  }
  
  public void setStartTime(int startTime) {
    this.startTime = startTime;
  }
  
  public int getEndTime() {
    return endTime;
  }
  
  public void setEndTime(int endTime) {
    this.endTime = endTime;
  }
  
  
}
