import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.sun.xml.internal.rngom.parse.compact.EOFException;

public class ReverseLine extends MigratableProcesses {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransactionalFileInputStream inFile;
	private TransactionalFileOutputStream outFile;

	public ReverseLine(int value) {
		super(value);
	}

	@Override
	public void run() {
		PrintStream out = new PrintStream(outFile);
		DataInputStream in = new DataInputStream(inFile);
		try {
			while(getSignal() == 0){
				String line = in.readLine();
				String output = "";
				for(int x = line.length(); x >= 0; x--)
				{
					output += line.charAt(x);
				}
				out.println(output);
			}
			
		}
		catch(EOFException e ) {
			//Put something in here
		}
		catch(IOException e) {
			System.out.println("ReverseLineProcess Error:" + e);
		}
	}

	@Override
	public void suspend() {
		while (true) {
			if (this.getSignal() != 0) {
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "ReverseLine:Instance Value:" + this.getInstanceValue()
				+ ":Arguments:" + this.getArguments();
	}

}
