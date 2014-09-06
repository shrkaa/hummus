import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class TransactionalFileOutputStream extends java.io.OutputStream
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String filename;
	private long seekTo;
	private RandomAccessFile raf;

	// GREP had a false argument in constructor???
	public TransactionalFileOutputStream(String file) {
		filename = file;
		seekTo = 0;
	}

	@Override
	public void write(int arg) throws IOException {
		raf = new RandomAccessFile(filename, "r");
		raf.seek(seekTo);
		raf.write(arg);
	}

	public RandomAccessFile getRandomAccessFile() {
		return raf;
	}

}
