package scheduler;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import dataStructures.Process;

public class Scheduler
{
	public LinkedList<Process> processes;
	public int clock = 0; 
	public boolean error = false; 
	
	public Scheduler(String input) {
		processes = new LinkedList<Process>();
		
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
		for (Process process : processes)
			process.PrintProcess();
	}
	
	public boolean AllProcessesCompleted() {
		for (Process process : processes) {
			if ( ! process.completed)
				return false; 
		}
		
		return true; 
	}
	
	public void Print() {
		if (error) {
			System.out.println("error");
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
		
		System.out.print(df.format(avg_turaround_time) + " ");
		
		for (Process process: processes) {
			process.PrintProcess();
		}
		
		System.out.println("");
	}
	
}
