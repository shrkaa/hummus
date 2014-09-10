package edu.cmu.cs.cs440.hw1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//TODO : NEED TO STOP THE PROCESSSERVER SOCKET???!!!!!!!
public class ProcessServerSocket implements Runnable {

	private ServerSocket server;
	private ProcessManager manager;
	public static final int PORT = 123;
	int isAlive;

	/**
	 * Constructor that creates a ProcessServerSocket. This binds a ServerSocket
	 * to a port and once done instatiates the isAlive variable to 1, indicating
	 * that the server has been created and alive. It also carries over the information
	 * from ProcessManager and creates a local version of it.
	 */
	public ProcessServerSocket(ProcessManager manager) {
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
        this.manager = manager;
		isAlive = 1;
	}

	/**
	 * The listen() method listens for incoming connections and creates a new
	 * thread when a connection has been made.
	 */
	public void listen()
	{
		Socket clientSocket = null;
	    try {
			clientSocket = server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    new Thread(new ProcessClientSocket(clientSocket,manager)).start();
		
	}

	@Override
	public void run() {
		while (isAlive == 1) {
          listen();
		}

	}

}
