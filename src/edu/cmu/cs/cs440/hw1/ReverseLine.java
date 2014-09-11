package edu.cmu.cs.cs440.hw1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.lang.InterruptedException;

/**
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan This is one of our examples
 *         of a MigratableProcess.
 * 
 *         ReverseLine is a function that takes an input file,outputfile. For
 *         each line in the input file, this function will reverse it and put it
 *         into outpufile ie: "Shri likes appples" would be ..
 *         "selppa sekil irhS"
 */
public class ReverseLine extends MigratableProcess {

	private static final long serialVersionUID = 1L;
	private TransactionalFileInputStream inFile;
	private TransactionalFileOutputStream outFile;

	/**
	 * Constructor that creates a RevereseLine process
	 * 
	 * @param value
	 *            - this is the processID
	 * @param args
	 *            - Array of arguments, ie: arg(0) is input file, arg(1) is
	 *            outputfile
	 * @throws Exception
	 *             - This exception will be thrown if the wrong parameters were
	 *             given
	 */
	public ReverseLine(int value, ArrayList<String> args) throws Exception {
		super(value, args);
		if (args.size() != 2) {
			System.out.println("Usage: ReverseLine <InputFile> <OutputFile>");
			throw new Exception("Invalid Arguments");
		}
		inFile = new TransactionalFileInputStream(args.get(0),this);
		outFile = new TransactionalFileOutputStream(args.get(1), this);
	}

	/**
	 * Called when the class is instantiated and thread has started. Reads
	 * everyline, reverses it and writes it out to outputfile.
	 */
	@Override
	public void run() {
		PrintStream out = new PrintStream(outFile);
		DataInputStream in = new DataInputStream(inFile);
		String line;
		try {
			while (((line = in.readLine()) != null) && !isSuspended()){
					String output = "";
					for (int x = line.length() - 1; x >= 0; x--) {
						output += line.charAt(x);
						/* Make this program longer so we can observe it */
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// Ignore it
						}
					}
				out.println(output);
			}
			setDone(true);
			System.out.println(isSuspended());
			System.out.println("I am done!!!! :D");
			in.close();
			out.close();
		} catch (EOFException e) {
			// Put something in here
		} catch (IOException e) {
			System.out.println("ReverseLineProcess Error:" + e);
		}

	}

	/**
	 * Prints out charactersitics about the process, such as processID, and the
	 * arguments
	 */
	@Override
	public String toString() {
		return "ReverseLine:ProcessID:" + this.getProcessID() + ":Arguments:"
				+ this.getArguments();
	}

}
