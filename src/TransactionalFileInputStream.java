import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class TransactionalFileInputStream extends java.io.InputStream implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String filename;
	private long seekTo;
	private RandomAccessFile raf;

	public TransactionalFileInputStream(String file) {
		filename = file;
		seekTo = 0;
	}

	@Override
	public int read() throws IOException {
		raf = new RandomAccessFile(filename, "r");
		raf.seek(seekTo);
		int bytes = raf.read();
		seekTo = seekTo + 1;
		return bytes;
	}
	
	//** MAKE SURE TO CLOSE RANDOMACCESSFILE!!!!!!
	public RandomAccessFile getRandomAccessFile()
	{
		return raf;
	}

}
