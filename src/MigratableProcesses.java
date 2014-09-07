import java.util.ArrayList;

public abstract class MigratableProcesses implements java.io.Serializable, java.lang.Runnable
		 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> arguments;
	private int instanceValue;
	private int signal;

	public MigratableProcesses(int value, ArrayList<String> args ) throws Exception {
		this.instanceValue = value;
		arguments = args;
	}

	/**
	 * Called before an object is serialized to allow the process to enter a
	 * known safe state
	 */
	public abstract void suspend();

	/**
	 * This method produces a symbol string representation of the object by
	 * printing the class name of the process as well as the original set of
	 * arguments in which it was called
	 */
	@Override
	public abstract String toString();
	
	public int getSignal() {
		return signal;
	}
	
	public ArrayList<String> getArguments()
	{
		return arguments;
	}
	
	public int getInstanceValue()
	{
		return instanceValue;
	}
}
