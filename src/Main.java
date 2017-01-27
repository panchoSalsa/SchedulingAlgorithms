import fileHandlers.InputFileHandler;
import scheduler.Scheduler;
import schedulingAlgorithms.FIFO;
import schedulingAlgorithms.MLF;

public class Main
{	
	public static void main(String[] args)
	{
		System.out.println("**start of Lab2**");
		
//		InputFileHandler input_file_handler = new InputFileHandler("/Volumes/USB DISK/CS143B/input.txt");
//		OutputFileHandler output_file_handler = new OutputFileHandler("/Volumes/USB DISK/CS143B/8926501.txt");
		
		InputFileHandler input_file_handler = new InputFileHandler("input.txt");
		
//		OutputFileHandler output_file_handler = new OutputFileHandler("8926501.txt");

		
		// this line will print the first init process
				
		while (! input_file_handler.end_of_file) {
			// driver ...
//			output_file_handler.Print(resource_manager.GetReply());
//			System.out.println(input_file_handler.current_line);
//			Scheduler scheduler = new Scheduler(input_file_handler.current_line);
//			scheduler.PrintProcessesState();
			
			
			// working code
			FIFO fifo = new FIFO(input_file_handler.current_line);
			fifo.Run();
			fifo.Print();
			
			// working code
			MLF mlf = new MLF(input_file_handler.current_line);
			mlf.Run();
			mlf.Print();
			
			
			input_file_handler.GetNextLine();
		}
		
//		output_file_handler.Close();
		System.out.println("**ending of Lab2**");
	}
	
}

