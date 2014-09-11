package edu.cmu.cs.cs440.hw1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProcessRecieverSocket implements Runnable {

	private Socket client;
	private ProcessManager manager;

	public ProcessRecieverSocket(Socket clientSocket, ProcessManager manager) {
		client = clientSocket;
		this.manager = manager;
	}

	@Override
	
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
