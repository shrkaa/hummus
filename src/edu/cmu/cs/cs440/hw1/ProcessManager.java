package edu.cmu.cs.cs440.hw1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 *         ProcessManager is responsible for creating, starting,and removing
 *         processes that are on the given Node
 */
public class ProcessManager {

	private int idCounter;
	private ArrayList<MigratableProcess> processes;

	/**
	 * Constructor for ProcessManager which instantiates... idCounter: int
	 * representing the current processID so that all processes are assigned a
	 * unique ID processes: is an ArrayList of MigratableProcesses. This is how
	 * the manager keeps track of the processes that are running
	 */
	public ProcessManager() {
		idCounter = 0;
		processes = new ArrayList<MigratableProcess>();
	}

	/**
	 * Creates a new MigratableProcess using reflection. It finds the
	 * constructor needed to instantiate the object and then starts the process
	 */
	public void createAndStartProcess(String processName, ArrayList<String> args)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		/* Create Process */
		Class myObjectClass = Class.forName(processName);
		Constructor constructor = myObjectClass.getConstructor(new Class[] {
				int.class, ArrayList.class });
		MigratableProcess newProcess = (MigratableProcess) constructor
				.newInstance(0, args);
		/* Start Process */
		startProcess(newProcess);
	}

	/**
	 * This method first unsuspends the process, assigns the proper processID,
	 * and creates a thread with the process running.
	 * 
	 * @param process
	 *            : Process starts
	 */
	public void startProcess(MigratableProcess process) {
		process.setSuspend(false);
		process.setProcessID(idCounter);
		new Thread(process).start();
		processes.add(process);
		idCounter++;
	}

	/**
	 * MigrateProcess, migrates a process to a given hostname using sockets.
	 * 
	 * @param process
	 * @param hostname
	 */
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

	/**
	 * This method returns only the processes that are still running, and
	 * updates the processes array whenever it is called.
	 * 
	 * @return updated processes array with only running processes
	 */
	public ArrayList<MigratableProcess> getProcesses() {

		for (int x = 0; x < processes.size(); x++) {
			if (processes.get(x).isDone())
				processes.remove(x);
			processes.remove(x);
		}

		return processes;
	}

	/**
	 * This finds a process given its id, if it doesn't exists it returns null
	 * 
	 * @param id
	 * @return MigratableProcess with the given id, if none exists it returns
	 *         null
	 */
	public MigratableProcess findProcess(int id) {
		for (int x = 0; x < processes.size(); x++) {
			MigratableProcess process = processes.get(x);
			if (process.getProcessID() == id && !process.isDone())
				return process;
		}

		return null;
	}

}
