import java.util.LinkedList;

/**
 * Printer Queue Tester Class
 * Solution to programming project #4.5
 * @author ebakan
 *
 */
public class PrinterQueueBakan {
	
	/**
	 * Main method
	 * @param args cli args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the print manager");
		final int MINPAGES=1;
		final int MAXPAGES=50;
		final int NUMJOBS=100;
		final int NUMPRINTERS=3;
		LinkedList<PrintJob> jobs = PrintJobGenerator.GenerateRandomJobs(MINPAGES, MAXPAGES, NUMJOBS);
		Printer[] printers = new Printer[NUMPRINTERS];
		for(int i=0;i<NUMPRINTERS;i++)
			printers[i]=new Printer(""+(i+1));
		PrinterJobDistributor distributor = new PrinterJobDistributor(printers);
		distributor.ExecuteJobs(jobs);
	}
}
