package scheduler;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import dataStructures.Process;
import fileHandlers.OutputFileHandler;

// TO-DO
// 1. add output file handler class to project
// 2. create object inside Scheduler class
// 3. use it to print to text file inside this project


public class Scheduler
{
	public LinkedList<Process> processes;
	public int clock = 0; 
	public boolean error = false; 
	public OutputFileHandler output_file_handler;

	public Scheduler(String input, OutputFileHandler output_file_handler) {
		processes = new LinkedList<Process>();
		this.output_file_handler = output_file_handler;
		
		InitializeProcesses(input);
	}
	
	private void InitializeProcesses(String input) {
		String[] pairs = input.split(" ");
		int pid, arrival_time, time_remaining; 
		
		for (int i = 0; i < pairs.length; i += 2) {
			pid = i / 2; 
			arrival_time = Integer.parseInt(pairs[i]);
			time_remaining = Integer.parseInt(pairs[i+1]);
			
			processes.add(new Process(pid,arrival_time,time_remaining));
		}
	}
	
	public void PrintProcessesState() {
		StringBuilder sb = new StringBuilder(64);
		for (Process process : processes)
			process.PrintProcess(output_file_handler);
//		return sb.toString();
	}
	
	public boolean AllProcessesCompleted() {
		for (Process process : processes) {
			if ( ! process.completed)
				return false; 
		}
		
		return true; 
	}
	
	public void Print() {
//		StringBuilder sb = new StringBuilder(64);
//		sb.append("select id1, ");
//		sb.append(id2);
//		sb.append(" from ");
//		sb.append(table);
	
		
		
		if (error) {
			//System.out.println("error");
			output_file_handler.Print("error");
//			sb.append("error");
//			return sb.toString(); 
			return;
		}
		
		int n = 0; 
		double total_turnaround_time = 0; 
		
		for (Process process: processes) {
			++n;
			total_turnaround_time += process.GetTurnAroundTime();
		}
		
		double avg_turaround_time = total_turnaround_time / n; 
		DecimalFormat df = new DecimalFormat(".00");
		
		df.setRoundingMode(RoundingMode.DOWN); // Note this extra step
		
//		System.out.print(df.format(avg_turaround_time) + " ");
		output_file_handler.Print(df.format(avg_turaround_time) + " ");
//		sb.append(df.format(avg_turaround_time) + " ");
		
		for (Process process: processes) {
//			process.PrintProcess();
			process.PrintProcess(output_file_handler);
//			sb.append(process.PrintProcess());
		}
		
		//System.out.println("");
		output_file_handler.Print("\n");
//		sb.append("\n");
//		
//		return sb.toString();
	}
	
}
