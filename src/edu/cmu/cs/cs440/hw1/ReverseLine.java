package edu.cmu.cs.cs440.hw1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.lang.InterruptedException;
import com.sun.xml.internal.rngom.parse.compact.EOFException;

public class ReverseLine extends MigratableProcess {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransactionalFileInputStream inFile;
	private TransactionalFileOutputStream outFile;

	public ReverseLine(int value, ArrayList<String> args) throws Exception {
		super(value, args);
		if (args.size() != 2) {
			System.out.println("Usage: ReverseLine <InputFile> <OutputFile>");
			throw new Exception("Invalid Arguments");
		}
		inFile = new TransactionalFileInputStream(args.get(0));
		outFile = new TransactionalFileOutputStream(args.get(1));
	}

	@Override
	public void run() {
		PrintStream out = new PrintStream(outFile);
		DataInputStream in = new DataInputStream(inFile);
		String line;
		try {
			while (!isSuspended()) {
				while((line = in.readLine()) != null){
				String output = "";
				for (int x = line.length() -1; x >= 0; x--) {
					output += line.charAt(x);
				}
				out.println(output);
			}
			}
		} catch (EOFException e) {
			// Put something in here
		} catch (IOException e) {
			System.out.println("ReverseLineProcess Error:" + e);
		}
	}

	@Override
	public String toString() {
		return "ReverseLine:Instance Value:" + this.getProcessID()
				+ ":Arguments:" + this.getArguments();
	}

}
