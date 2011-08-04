/**
 * Represents a print job of a certain number of pages
 * (Used rather than a stack of objects because
 * we don't care what we're printing - we just
 * need the number of pages in a certain job)
 * @author ebakan
 *
 */
public class PrintJob {
	
	/**
	 * Constructor: creates a new PrintJob
	 * with a certain number of pages
	 * @param numPages number of pages in the PrintJob
	 */
	public PrintJob(int numPages) {
		if(numPages<=0)
			throw new IllegalArgumentException("Cannot have a PrintJob with less than 1 page");
		this.numPages=numPages;
		this.pagesPrinted=0;
	}
	
	/**
	 * Simulates printing one page of the PrintJob
	 * @return the number of pages left in the PrintJob
	 */
	public int PrintPage() {
		if(pagesPrinted==numPages)
			throw new IllegalStateException("PrintJob empty. Cannot print additional pages.");
		//System.out.printf("Page %d of %d printed.\n",++pagesPrinted,numPages);
		++pagesPrinted;
		return numPages-pagesPrinted;
	}
	
	/**
	 * Returns the size of the current job
	 * @return current job size
	 */
	public int GetJobSize() {
		return numPages;
	}
	
	/**
	 * Represents the total number of pages in the PrintJob
	 */
	private int numPages;
	
	/**
	 * Represents the number of pages printed so far in the PrintJob
	 */
	private int pagesPrinted;
}
