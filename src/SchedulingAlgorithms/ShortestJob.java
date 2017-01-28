package schedulingAlgorithms;

import java.util.LinkedList;
import scheduler.Scheduler;
import dataStructures.Process;
import fileHandlers.OutputFileHandler; 

public class ShortestJob extends Scheduler
{
	
	private boolean preemption_flag;
	public LinkedList<Process> ready_list;
	private Process next; 
	
	public ShortestJob(String input, boolean preemption_flag, OutputFileHandler output_file_handler) {
		super(input, output_file_handler);
		
		this.preemption_flag = preemption_flag; 
		ready_list = new LinkedList<Process>();
		next = null; 
	}
	
	public void Run() {
		while (!AllProcessesCompleted()) {
			
			UpdateReadyState();
				
			// if preemption is set
			if (preemption_flag)
				GetNextProcessUsingPreemption();
			else 
				GetNextProcessNoPreemption();
				
			
			if (next != null) {
				Execute(next);
			}
			
			
			
			++clock; 
		}
	}
	
	private void UpdateReadyState()
	{
		for (Process process: processes) {
			if (process.arrival_time == clock) {
				process.ready = true; 
				ready_list.add(process);
			}
		}
		
	}
	
//	private Process GetNextProcessUsingPreemption()
//	{
////		Process next = null; 
//		if (! ready_list.isEmpty()) {
//			for (Process process: ready_list) {
//				if (next == null) {
//					// set next to the first one
//					next = process;
//				} else {
//					if (next.time_remaining > process.time_remaining)
//						next = process;
//				}
//			}
//			
//			return next; 
//			
//		} else {
//			return null; 
//		}
//	}
	
	private void GetNextProcessUsingPreemption()
	{
//		Process next = null; 
		if (! ready_list.isEmpty()) {
			for (Process process: ready_list) {
				if (next == null) {
					// set next to the first one
					next = process;
				} else {
					if (next.time_remaining > process.time_remaining)
						next = process;
				}
			}
			
		}
		
	}
	
	
	private void GetNextProcessNoPreemption()
	{
		if (next == null) {
			GetNextProcessUsingPreemption();
		}
	}
	
	
	private void Execute(Process process) {
		process.ExecuteOneClockCycle();
		
		if (process.Completed()) {
			process.RecordFinishTime(clock);
			
			// lets pray we can remove from within any location in the list ... 
			ready_list.remove(process);
			next = null; 
		}
		
	}
}
