package dataStructures;

import java.util.LinkedList;

public class PriorityQueue
{
	public int level;
	public int T; 
	public int t;
	public LinkedList<Process> priority_queue; 
	public PriorityQueue lower_queue;
	
	public PriorityQueue(int level, int T, PriorityQueue lower_queue) {
		this.level = level;
		this.lower_queue = lower_queue;
		this.T = this.t = T;
		priority_queue = new LinkedList<Process>();
	}
	
	public boolean IsEmpty() {
		return priority_queue.isEmpty();
	}
	
	public void Add(Process process) {
		priority_queue.add(process);
	}
	
	public boolean Execute(int clock) {
		Process process = priority_queue.getFirst();
		
		process.ExecuteOneClockCycle();
		
		--t;
		
		if (process.Completed()) {
			process.RecordFinishTime(clock);
			
			// remove it from queue;
			priority_queue.removeFirst();
			t = T; 
		} else if (t == 0) {
			// time has expired for this process
			// move it to lower level 
			priority_queue.removeFirst();
			if (lower_queue == null)
				return true; 
			
			lower_queue.Add(process);
			t = T; 
		}
		
		return false; 
	}
			
}
