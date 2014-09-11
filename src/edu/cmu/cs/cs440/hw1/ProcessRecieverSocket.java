package edu.cmu.cs.cs440.hw1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class is designed to handle the MigratableProcesses that have migrated
 * over. This class starts those processes back up on this node.
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 */
public class ProcessRecieverSocket implements Runnable {

	private Socket client;
	private ProcessManager manager;

	/**
	 * Constructor that Creates a ProcessRecieverSocket
	 * 
	 * @param clientSocket
	 *            - has the client socket in which the information is following
	 *            through
	 * @param manager
	 *            - Has a copy of the ProcessManager in order to start processes
	 */
	public ProcessRecieverSocket(Socket clientSocket, ProcessManager manager) {
		client = clientSocket;
		this.manager = manager;
	}

	@Override
	/**
	 * Since this implements Runnable, this method will be run when this class is 
	 * instaniated and started in a new thread.
	 * This will read in the new MigratableProcess from the input stream and start that process back up
	 */
	public void run() {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(client.getInputStream());
			MigratableProcess process = (MigratableProcess) in.readObject();
			manager.startProcess(process);
			in.close();
			client.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
