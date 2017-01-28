package schedulingAlgorithms;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import scheduler.Scheduler;
import dataStructures.PriorityQueue;
import dataStructures.Process;
import fileHandlers.OutputFileHandler;
import java.lang.Math;

// MLF does not handle when the times are long
// Actually i still need to test the output

public class MLF extends Scheduler
{	
	// you should never remove any items from the priority_list
	public LinkedList<PriorityQueue> priority_list;
	public LinkedList<Process> ready_list;
	int n = 0; 
	
	public MLF(String input, OutputFileHandler output_file_handler) {
		super(input, output_file_handler);
		ready_list = new LinkedList<Process>();
		priority_list = new LinkedList<PriorityQueue> ();
		
		InitializePriorityList();
	}
	
	private void InitializePriorityList() {
//		PriorityQueue first_level = new PriorityQueue(1,16,null);
//		PriorityQueue second_level = new PriorityQueue(2,8,first_level);
//		PriorityQueue third_level = new PriorityQueue(3,4,second_level);
//		PriorityQueue fourth_level = new PriorityQueue(4,2,third_level);
//		PriorityQueue fifth_level = new PriorityQueue(5,1,fourth_level);
//		
//		priority_list.add(fifth_level);
//		priority_list.add(fourth_level);
//		priority_list.add(third_level);
//		priority_list.add(second_level);
//		priority_list.add(first_level);
		
		
		// if correct this might be algorithm we need
		
		n = processes.size();
		int limit = n; 
		PriorityQueue lower = null; 
		PriorityQueue temp = null;
		
		for (int i = 1; i <= limit; ++i) {
			temp = new PriorityQueue(i,TwoToThePowerOfN(n),lower);
			priority_list.add(temp);
			lower = temp;
		}
		

		Collections.reverse(priority_list);
	}
	
	public int TwoToThePowerOfN(int n) {
		double answer = Math.pow(2.0,3.0);
		return  (int) answer;
	}
	
	public void Run() {
		
		PriorityQueue highest_priority; 
		
		while (! AllProcessesCompleted() && ! error) {
			
			UpdateReadyList();
			MoveFromReadyListToFifthPriority();
			
			highest_priority = GetHighestPriority();
			
			if (highest_priority != null) {
				// only execute the process for one cycle
				error = highest_priority.Execute(clock);
			}
			
			++clock; 
		}
	}
	
	private PriorityQueue GetHighestPriority()
	{
		for (PriorityQueue priority: priority_list) {
			if (! priority.IsEmpty())
				return priority; 
		}
		
		return null; 
	}

	public void UpdateReadyList() {
		for(Process process: processes) {
			if (process.arrival_time == clock) {
				process.ready = true; 
				ready_list.add(process);
			}
		}
	}
	
	public void MoveFromReadyListToFifthPriority() {
		
		// add all ready processes to the fifth priority queue
		for (Process process : ready_list) {
			priority_list.getFirst().Add(process);
		}
		
		// empty the ready list for the next clock cycle
		ready_list.clear();
	}	
}
