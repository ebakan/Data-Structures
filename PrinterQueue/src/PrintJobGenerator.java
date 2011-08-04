import java.util.LinkedList;
import java.util.Random;

/**
 * Generates PrintJobs
 * @author ebakan
 *
 */
public class PrintJobGenerator {
	
	/**
	 * Generates a Queue of PrintJobs of varying lengths
	 * @param minPages Minimum number of pages per job
	 * @param maxPages Maximum number of pages per job
	 * @param numJobs Number of jobs to be generated
	 * @return LinkedList of PrintJobs
	 */
	public static LinkedList<PrintJob> GenerateRandomJobs(int minPages, int maxPages, int numJobs) {
		if(minPages<=0)
			throw new IllegalArgumentException("Must have a positive number of minimum pages per job");
		if(maxPages<=0)
			throw new IllegalArgumentException("Must have a positive number of maximum pages per job");
		if(maxPages<minPages)
			throw new IllegalArgumentException("Maximum number of pages must be greater than or equal to the minimum number of pages");
		if(numJobs<=0)
			throw new IllegalArgumentException("Must have a positive number of jobs");
		
		LinkedList<PrintJob> jobs = new LinkedList<PrintJob>();
		Random rand = new Random();
		
		for(int i=0;i<numJobs;i++)
			jobs.offer(new PrintJob(minPages+rand.nextInt(maxPages-minPages)));
		
		return jobs;
	}
}
