/**
 * Represents a printer with a specific print job
 * @author ebakan
 *
 */
public class Printer {
	
	/**
	 * Constructs a printer with a specific name
	 * @param name this Printer's name
	 */
	public Printer(String name) {
		this.name=name;
	}
	
	/**
	 * Adds a new job to the printer
	 * @param job PrintJob to be added
	 */
	public void NewJob(PrintJob job) {
		this.job=job;
	}
	
	/**
	 * Simulates the printing of a page on the printer
	 */
	public void PrintPage() {
		if(job==null)
			System.out.println("No current print job.");
		else if(job.PrintPage()==0) {
				System.out.printf("Printer %s: %d-page job finished. Took %.2f minute(s).\n",name,job.GetJobSize(),job.GetJobSize()/10.0);
				job=null;
		}
	}
	
	/**
	 * Simulates the printing of a number of pages on the printer
	 * @param numPages the number of pages to be printed
	 * @return number of pages printed
	 */
	public int PrintPages(int numPages) {
		if(numPages<=0)
			throw new IllegalArgumentException("Must have a positive number of pages to be printed");
		for(int i=0;i<numPages;i++) {
			if(job!=null)
				PrintPage();
			else
				return i;
		}
		return numPages;
	}
	
	/**
	 * Gets the current state of the printer
	 * Can be expanded to include printer jams,
	 * low ink, etc (enums would provide further
	 * clarity at that point)
	 * @return 1 if printing, 0 if idle
	 */
	public int GetState() {
		if(job==null)
			return 0;
		else
			return 1;
	}
	
	/**
	 * Name of the printer
	 */
	private String name;
	
	/**
	 * Job the printer is currently processing
	 */
	private PrintJob job;
}
