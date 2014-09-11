package edu.cmu.cs.cs440.hw1;
import java.util.ArrayList;

/**
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 * This class is an abstract class that defines the structure for a MigratableProcess.
 * It is a generic template so that all MigratableProcesses can be made in the future.
 */
public abstract class MigratableProcess implements java.lang.Runnable,
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> arguments;
	private int processID;
	private boolean suspendedSignal;
	private boolean isDone;
	private boolean isMigrated;
    /**
     * Constructor that creates a MigratableProcess
     * @param value - processID
     * @param args - list of arguments that the process needs
     * @throws Exception
     */
	public MigratableProcess(int value, ArrayList<String> args )throws Exception {
		this.processID = value;
		arguments = args;
		suspendedSignal = false;
		isDone = false;
		isMigrated = false;
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
	
	public boolean isDone()
	{
		return isDone;
	}
	
	public void setDone(boolean flag)
	{
		isDone = flag;
	}
	
	public boolean isMigrated()
	{
		return isMigrated;
	}
	
	public void setMigrated(boolean flag)
	{
		isMigrated = flag;
	}
}
