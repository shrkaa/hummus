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
	private boolean isMigrated;

	public TransactionalFileInputStream(String file) {
		filename = file;
		seekTo = 0;
		isMigrated = false;
	}

	/**
	 * Reads in a file, if it has been opened for the first time or
	 * has been migrated it seeks to the proper place.
	 */
	@Override
	public int read() throws IOException {
		if(raf == null || isMigrated == true)
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
	
	/** Sets if the file has been migrated */
	public void setMigrated(boolean flag)
	{
		isMigrated = flag;
	}
	

}
