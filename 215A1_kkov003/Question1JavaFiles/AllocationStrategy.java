import java.util.ArrayList;

/* CS215 Assignment One 2013
 * Name:Ksenia Kovaleva
 * Id:4716583
 * upi:kkov003
 */

public abstract class AllocationStrategy {
  protected ArrayList<Job> Jobs;
  protected ArrayList<Job> Queue;
  
  public AllocationStrategy(ArrayList<Job> jobs) {
    super();
    Jobs = jobs;
  }
  
  public abstract void run();
  
}
