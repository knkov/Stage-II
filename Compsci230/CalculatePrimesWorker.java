
public class CalculatePrimesWorker extends Thread {
	
	public volatile boolean finished = false;
	private volatile int maxPrimeFound = 0;
	public int maxPF = 0;
	
	private static int maxPrime = CalculatePrimesA3V1.maxPrime;
	private int workerNum; //unique thread ID
    private static int workerCount; // count of threads created

    public CalculatePrimesWorker() {
        workerCount++;
        workerNum = workerCount;
    }
    public int getWorkerNum(){
    	return workerNum;
    }
    protected int getMaxPrimeFound() { 
    	return maxPrimeFound; 
    }
    
	public void run() {
		int[] primes = new int[maxPrime];
		int count = 0;
		for (int i=2; i <= maxPrime; i++) {
			// Check to see if the timer has expired
			if (finished) {
				break;
			}
			boolean prime = true;
			for (int j=0; j<count; j++) {
				if (i % primes[j] == 0) {
					prime = false;
					break;
				}
			}
			if (prime) {
				primes[count++] = i;
				maxPrimeFound = i; 
				maxPF = i; 
			}
		}
	}

}
