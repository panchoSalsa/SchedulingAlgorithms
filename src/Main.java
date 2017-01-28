import fileHandlers.InputFileHandler;
import fileHandlers.OutputFileHandler;
import schedulingAlgorithms.FIFO;
import schedulingAlgorithms.MLF;
import schedulingAlgorithms.ShortestJob;

public class Main
{	
	public static void main(String[] args)
	{
		System.out.println("**start of Lab2**");
		
//		InputFileHandler input_file_handler = new InputFileHandler("/Volumes/USB DISK/CS143B/input.txt");
//		OutputFileHandler output_file_handler = new OutputFileHandler("/Volumes/USB DISK/CS143B/8926501.txt");
		
		InputFileHandler input_file_handler = new InputFileHandler("input.txt");
		OutputFileHandler output_file_handler = new OutputFileHandler("8926501.txt");
		
		
		// driver ...
				
		while (! input_file_handler.end_of_file) {
			
			FIFO fifo = new FIFO(input_file_handler.current_line, output_file_handler);
			fifo.Run();
			fifo.Print();
			
			ShortestJob shortest_job_first = new ShortestJob(input_file_handler.current_line, false, output_file_handler);
			shortest_job_first.Run();
			shortest_job_first.Print();
			
			
			ShortestJob shortest_job_remaining = new ShortestJob(input_file_handler.current_line, true, output_file_handler);
			shortest_job_remaining.Run();
			shortest_job_remaining.Print();
			
			MLF mlf = new MLF(input_file_handler.current_line, output_file_handler);
			mlf.Run();
			mlf.Print();
			
			output_file_handler.Print("\n");
			
			
			input_file_handler.GetNextLine();
		}
		
		output_file_handler.Close();
		System.out.println("**ending of Lab2**");
	}
	
}

