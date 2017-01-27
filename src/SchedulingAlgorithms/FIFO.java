package schedulingAlgorithms;

import java.util.LinkedList;
import scheduler.Scheduler;
import dataStructures.Process; 

// FIFO might have errors
// 0 100 50 100 60 20
// 136.66 100 150 160 

public class FIFO extends Scheduler
{
	public LinkedList<Process> ready_list; 
	
	public FIFO(String input) {
		super(input);
		
		ready_list = new LinkedList<Process>();
		
		
	}
	
	public void Run() {
		while (! AllProcessesCompleted()) {
			
			UpdateReadyState();
			Process next = GetNextProcess();
			
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
	
	private Process GetNextProcess() {
		if (! ready_list.isEmpty()) 
			return ready_list.getFirst();
		else 
			return null; 
	}
	
	private void Execute(Process process) {
		process.ExecuteOneClockCycle();
		
		if (process.Completed()) {
			process.RecordFinishTime(clock);
			ready_list.removeFirst();
		}
		
	}
}
