package edu.cmu.cs.cs440.hw1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * 
 * @author Shri Karthikeyan and Nandini Ramakrishnan
 * 
 *         This is one of our examples of our MigratableProces.
 * 
 *         The ReplaceProcess takes in an inputfile, outputfile, findString, and
 *         ReplaceString.
 * 
 *         It reads in the inputfile and looks for the findString and replaces
 *         every instance with the ReplaceString and outputs the result in the
 *         outputfile.
 * 
 */
public class ReplaceProcess extends MigratableProcess {

	private static final long serialVersionUID1 = 1L;
	private TransactionalFileInputStream inFile;
	private TransactionalFileOutputStream outFile;
	private String findString;
	private String replaceString;

	/**
	 * Constructor that create a new ReplaceProcess.
	 * 
	 * @param value
	 *            - processID
	 * @param args
	 *            - list of arguments. arg(0) - input file , arg(1) - output
	 *            file, arg(2) - findString, arg(3) - replaceString
	 * @throws Exception
	 */
	public ReplaceProcess(int value, ArrayList<String> args) throws Exception {
		super(value, args);
		if (args.size() != 4) {
			System.out
					.println("Usage: ReverseLine <InputFile> <OutputFile> <FindString> <ReplaceString>");
			throw new Exception("Invalid Arguments");
		} else {
			inFile = new TransactionalFileInputStream(args.get(0));
			outFile = new TransactionalFileOutputStream(args.get(1));
			findString = args.get(2);
			replaceString = args.get(3);
		}
	}

	private static final long serialVersionUID = 1L;

	/**
	 * Called when class is instatiated and new thread is started. The function
	 * looks for the FindString and Replaces it with ReplaceString and writes to
	 * outPutFile.
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		PrintStream out = new PrintStream(outFile);
		DataInputStream in = new DataInputStream(inFile);
		String line;
		try {
			while (!isSuspended()) {
				while ((line = in.readLine()) != null) {
					String[] words = line.split(" ");
					String output = "";
					for (int x = 0; x < words.length; x++) {
						if (words[x].equals(findString)) {
							output += replaceString + " ";
						}

						else
							output += words[x] + " ";

						/* Make this program longer so we can observe it */
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							System.out.println("I got interupted!");
						}
					}
					out.println(output);
				}
				setDone(true);
			}
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
		return "ReplaceStringProcess:ProcessID:" + this.getProcessID()
				+ ":Arguments:" + this.getArguments();
	}

}
