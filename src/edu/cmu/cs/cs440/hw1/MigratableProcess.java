package edu.cmu.cs.cs440.hw1;
import java.util.ArrayList;

public abstract class MigratableProcess implements java.lang.Runnable,
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> arguments;
	private int processID;
	private boolean suspendedSignal;

	public MigratableProcess(int value, ArrayList<String> args )throws Exception {
		this.processID = value;
		arguments = args;
		suspendedSignal = false;
	}

	/**
	 * Called before an object is serialized to allow the process to enter a
	 * known safe state
	 */
	public void suspend()
	{
		while(suspendedSignal);
	}

	/**
	 * This method produces a symbol string representation of the object by
	 * printing the class name of the process as well as the original set of
	 * arguments in which it was called
	 */
	@Override
	public abstract String toString();
	
	
	public ArrayList<String> getArguments()
	{
		return arguments;
	}
	
	public int getProcessID()
	{
		return processID;
	}
	
	public void setProcessID(int value)
	{
		processID = value;
	}
	
	public void setSuspend(boolean flag)
	{
		suspendedSignal = flag;
	}
	
	public boolean isSuspended()
	{
		return suspendedSignal;
	}
}
