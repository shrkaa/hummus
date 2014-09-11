package edu.cmu.cs.cs440.hw1;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class TransactionalFileOutputStream extends java.io.OutputStream
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String filename;
	private long seekTo;
	private transient RandomAccessFile raf;
	private MigratableProcess process;
	
    /**
     * Constrctor that creates a TransactionalFileOutputStream
     * given the output file.
     * @param file - file to write output into
     */
	public TransactionalFileOutputStream(String file, MigratableProcess process) {
		filename = file;
		seekTo = 0;
		this.process = process;
	}

	/** 
	 * Writes into a given file and seeks to the correct place when appropraite
	 */
	@Override
	public void write(int arg) throws IOException {
		if(raf == null || process.isMigrated())
		{
			raf = new RandomAccessFile(filename, "rw");
			raf.seek(seekTo);
		}
		raf.write(arg);
		seekTo = seekTo + 1;
	}

	/** Closes the OutPutStream */
	public void close() throws IOException
	{
	  super.close();
	  if(raf != null)
		  raf.close();
	}
	

}
