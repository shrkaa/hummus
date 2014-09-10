package edu.cmu.cs.cs440.hw1;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import com.sun.xml.internal.rngom.parse.compact.EOFException;


public class ReplaceProcess extends MigratableProcess {
	
	private static final long serialVersionUID1 = 1L;
	private TransactionalFileInputStream inFile;
	private TransactionalFileOutputStream outFile;
	private String findString;
	private String replaceString;
	
	public ReplaceProcess(int value, ArrayList<String> args) throws Exception{
		super(value, args);
		if (args.size() != 4) {
			System.out.println("Usage: ReverseLine <InputFile> <OutputFile> <FindString> <ReplaceString>");
			throw new Exception("Invalid Arguments");
		}
		inFile = new TransactionalFileInputStream(args.get(0));
		outFile = new TransactionalFileOutputStream(args.get(1));
		findString = args.get(2);
		replaceString = args.get(3);
		
	}

	// TransactionalFileInputStream in
	
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		PrintStream out = new PrintStream(outFile);
		DataInputStream in = new DataInputStream(inFile);
		try {
			while (!isSuspended()) {
				String line = in.readLine();
				String [] words = line.split(" ");
				String output = "";
				for (int x = 0; x < words.length; x++) {
					if(words[x].equals(findString))
					{
						output += replaceString + " ";
					}
					
					else
						output += words[x]+ " ";
				}
				out.println(output);
			}

		} catch (EOFException e) {
			// Put something in here
		} catch (IOException e) {
			System.out.println("ReverseLineProcess Error:" + e);
		}
		
	}


	@Override
	public String toString() {
		return "ReplaceProcess:Instance Value:" + this.getProcessID()
				+ ":Arguments:" + this.getArguments();
	}
	
}
