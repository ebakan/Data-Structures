import java.util.LinkedList;

/**
 * Represents a distributor as described
 * in problem #5 to divvy up print tasks
 * among a set number of printers
 * @author ebakan
 *
 */
public class PrinterJobDistributor {
	/**
	 * Constructor, accepts a number of printers
	 * @param printers printers in the current network
	 */
	public PrinterJobDistributor(Printer[] printers) {
		this.printers=printers;
		this.jobQueues=new LinkedList<LinkedList<PrintJob>>();
		for(int i=0;i<3;i++)
			jobQueues.add(new LinkedList<PrintJob>());
	}
	
	/**
	 * Executes the jobs passed in, showing minutely updates
	 * @param jobs jobs to be executed
	 */
	public void ExecuteJobs(LinkedList<PrintJob> jobs) {
		int time=1;
		boolean printersIdle=true; //if all the printers are idle
		while(!jobs.isEmpty() || HaveJobsLeft() || !printersIdle) {
			System.out.println("Minute "+time);
			
			//get one new job per minute
			if(!jobs.isEmpty()) {
				PrintJob job = jobs.poll();
				int jobsize=job.GetJobSize();
				System.out.printf("Job of size %d recieved\n",jobsize);
				AddJob(job);
			}
			
			//handle printing for each printer
			for(int i=0;i<printers.length;i++) {
				Printer printer = printers[i];
				//add in a new job if needed
				if(printer.GetState()==0)
					printer.NewJob(GetNextJob());
				int pagescanprint=10; //max 10 pages/minute
				while(pagescanprint>0) {
					pagescanprint-=printer.PrintPages(pagescanprint);
					//get another job if can print more, jobs available
					if(pagescanprint>0) {
						PrintJob newjob = GetNextJob();
						if(newjob==null)
							break;
						else
							printer.NewJob(newjob);
					}
				}
			}
			//check if all printers are idle
			printersIdle=true;
			for(int i=0;i<printers.length;i++)
				if(printers[i].GetState()==1)
					printersIdle=false;
			time++;
			System.out.println();
		}
	}
	
	/**
	 * Adds a job into the proper queue
	 * @param job job to be processed and added
	 */
	private void AddJob(PrintJob job) {
		int jobsize=job.GetJobSize();
		if(jobsize<10)
			jobQueues.get(0).offer(job);
		else if(jobsize<20)
			jobQueues.get(1).offer(job);
		else
			jobQueues.get(2).offer(job);
	}
	
	/**
	 * Gets the next job in the queues
	 * according to the book's rules
	 * @return next job to be printed
	 */
	private PrintJob GetNextJob() {
		for(int i=0;i<3;i++)
			if(!jobQueues.get(i).isEmpty())
				return jobQueues.get(i).poll();
		return null;
	}
	
	/**
	 * Checks if any jobs are currently in the print queue
	 * @return if any jobs are left
	 */
	private boolean HaveJobsLeft() {
		for(int i=0;i<3;i++)
			if(!jobQueues.get(i).isEmpty())
				return true;
		return false;
	}
	
	/**
	 * Three queues as described in the book
	 */
	private LinkedList<LinkedList<PrintJob>> jobQueues;
	
	/**
	 * Current printers available to process print jobs
	 */
	private Printer[] printers;

}
