import java.util.*;

public class CalculatePrimesA3V1{
	
	
	
	public static int maxPrime;
	

	public static void main(String[] args) {
		
		int sleepTime = 10000; //arg[0] milliSec main will sleep
		int nWorkers = 1; // num of threads to be created - arg[1]
		maxPrime = 1000000; // arg[2] max prime to be computed
		
		
		try {
		if (args.length >= 3){
			sleepTime = Integer.parseInt(args[0]);
			nWorkers = Integer.parseInt (args[1]);
			maxPrime = Integer.parseInt(args[2]);
		
		}else if (args.length == 2){
			sleepTime = Integer.parseInt(args[0]);
			nWorkers = Integer.parseInt(args[1]);
		
		}else if (args.length == 1){
			sleepTime = Integer.parseInt(args[0]);
		
		}
		}catch (NumberFormatException e){
			System.out.println("Arguments entered are not integers.");
		 // do something if arguments not numbers
		}
		
		
		ArrayList <CalculatePrimesWorker> calculator = new ArrayList<CalculatePrimesWorker>(); // stores threads
		
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < nWorkers; i++) { // nWorkers are created and started
            CalculatePrimesWorker workerThread = new CalculatePrimesWorker();
            calculator.add(workerThread);
            workerThread.start();
		}
		for(int i=0;i<nWorkers;i++){ //snapshot 1 - after all threads started
			CalculatePrimesWorker worker = calculator.get(i);
			int mpf1 = worker.maxPF; 
			int mpf2 = worker.getMaxPrimeFound(); 
			if (mpf1 != mpf2) { 
			System.out.println("!! MaxPF from worker " 
			+ worker.getWorkerNum() + ": " + mpf1 
			+ " isn't equal to its maxPrimeFound() " + mpf2); 
			}else{
				System.out.println("Thread " + worker.getWorkerNum()+" Max Prime Found: "+ mpf2);
			}
		
		}
		
		try {
			Thread.sleep(sleepTime);
		}
		catch (InterruptedException e) {
			// fall through
		}
		for (int j = 0; j < calculator.size(); j++) {
            CalculatePrimesWorker workerThread = calculator.get(j);
            workerThread.finished = true;
        }
		
		for(int i=0;i<nWorkers;i++){ //snapshot 2 - after all threads finished
			CalculatePrimesWorker worker = calculator.get(i);
			int mpf1 = worker.maxPF; 
			int mpf2 = worker.getMaxPrimeFound(); 
			if (mpf1 != mpf2) { 
			System.out.println("!! MaxPF from worker " 
			+ worker.getWorkerNum() + ": " + mpf1 
			+ " isn't equal to its maxPrimeFound() " + mpf2); 
			}else{
				System.out.println("Thread " + worker.getWorkerNum()+" Max Prime Found: "+ mpf2);
			}
			
		
		}
		System.out.println("Runtime: " 
				+ (System.currentTimeMillis() - startTime) + " milliseconds"); 
	}
	
}

