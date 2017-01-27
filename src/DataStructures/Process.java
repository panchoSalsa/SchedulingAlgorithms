package dataStructures;

// can we assume time_remaining is > 0


public class Process
{
	public int pid; 
	public int arrival_time; 
	public int finish_time;
	public int time_remaining; 
	public boolean completed = false; 
	public boolean ready = false; 
	
	public Process(int pid, int arrival_time, int time_remaining) { 
		this.pid = pid; 
		this.arrival_time = arrival_time;
		this.time_remaining = time_remaining;
	}
	
	public int GetTurnAroundTime() {
		return finish_time - arrival_time; 
	}
	
	public void PrintProcess() {
//		System.out.println("pid: " + pid  + " , arrival_time: " + arrival_time + ", time_remaining: " + time_remaining);
		System.out.print(GetTurnAroundTime() + " ");
	}

	public void ExecuteOneClockCycle()
	{
		--time_remaining;
		
		if (time_remaining == 0)
			completed = true; 
	}
	
	public boolean Completed() {
		return completed; 
	}

	public void RecordFinishTime(int clock)
	{
		// for example if clock is 0, the process will finish when clock is 1. 
		finish_time = clock + 1; 
	}
}
