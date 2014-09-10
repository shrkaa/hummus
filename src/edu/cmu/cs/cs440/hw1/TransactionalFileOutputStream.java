package edu.cmu.cs.cs440.hw1;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class TransactionalFileOutputStream extends java.io.OutputStream
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String filename;
	private long seekTo;
	private RandomAccessFile raf;
	private boolean isMigrated;
	
	// GREP had a false argument in constructor???
	public TransactionalFileOutputStream(String file) {
		filename = file;
		seekTo = 0;
		isMigrated = false;
	}

	@Override
	public void write(int arg) throws IOException {
		if(raf == null || isMigrated)
		{
			raf = new RandomAccessFile(filename, "rw");
			raf.seek(seekTo);
		}
		raf.write(arg);
	}

	public void close() throws IOException
	{
	  super.close();
	  if(raf != null)
		  raf.close();
	}
	
	public void setMigrated(boolean flag)
	{
		isMigrated = flag;
	}

}
