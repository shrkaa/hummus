package edu.cmu.cs.cs440.hw1;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

//HAVE TO CHECK IF IT IS MIGRATED!!!
public class TransactionalFileInputStream extends java.io.InputStream implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String filename;
	private long seekTo;
	private transient RandomAccessFile raf;
	private MigratableProcess process;

	public TransactionalFileInputStream(String file, MigratableProcess process) {
		filename = file;
		seekTo = 0;
		this.process = process;
	}

	/**
	 * Reads in a file, if it has been opened for the first time or
	 * has been migrated it seeks to the proper place.
	 */
	@Override
	public int read() throws IOException {
		if(raf == null || process.isMigrated())
		{
			raf = new RandomAccessFile(filename, "r");
			raf.seek(seekTo);
		}
		int bytes = raf.read();
		seekTo = seekTo + 1;
		return bytes;
	}
	
	/** Closes the input stream */
	public void close() throws IOException
	{
	  super.close();
	  if(raf != null)
		  raf.close();
	}
	

	

}
