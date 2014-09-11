package edu.cmu.cs.cs440.hw1;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ProcessManager {

	private int idCounter;
	private ArrayList<MigratableProcess> processes;

	public ProcessManager() {
		idCounter = 0;
		processes = new ArrayList<MigratableProcess>();
	}

	public void createAndStartProcess(String processName,
			ArrayList<String> args) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		/* Create Process */
		Class myObjectClass = Class.forName(processName);
		Constructor constructor = myObjectClass.getConstructor(new Class[] {
				Integer.class, String.class });
		MigratableProcess newProcess = (MigratableProcess) constructor
				.newInstance(0, args);
		/* Start Process */
		startProcess(newProcess);
	}

	public void startProcess(MigratableProcess process) {
		process.setSuspend(false);
		process.setProcessID(idCounter);
		new Thread(process).start();
		processes.add(process);
		idCounter ++;
	}

	public void migrateProcess(MigratableProcess process, String hostname) {
		ObjectOutputStream out;
		Socket socket;
		process.setSuspend(true);
	    try {
			socket = new Socket(hostname, ProcessServerSocket.PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
		    out.writeObject(process);
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<MigratableProcess> getProcesses()
	{
		return processes;
	}
	public MigratableProcess findProcess(int id)
	{
		for(int x = 0; x <processes.size(); x++)
		{
			MigratableProcess process = processes.get(x);
			if(process.getProcessID() == id)
				return process;
		}
		
		return null;
	}
	public void removeProcess(){
		
	}

}
