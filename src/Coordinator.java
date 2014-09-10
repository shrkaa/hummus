import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Coordinator extends Thread {
	/*
	 * A node connects to the coordinator
	 */
	private ServerSocket serverSocket;

	public Coordinator(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	public void readSignal() {
		/*
		 * Deserialize the signal
		 * Read if it is create
		 */
	}
	
	public void run()
    {
      while(true)
      {
         try
         {
        	 Socket clientSocket = serverSocket.accept();
        	 DataOutputStream out =
                     new DataOutputStream(clientSocket.getOutputStream());
         } 
         catch (Exception e) {
        	 e.printStackTrace();
         }
      }
    }

	public static void main(String[] args) {

	}
}
