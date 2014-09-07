
public class ReplaceProcess extends MigratableProcesses {
	
	public ReplaceProcess(int value) {
		super(value);
	}

	// TransactionalFileInputStream in
	
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "ReplaceProcess:Instance Value:" + this.getInstanceValue()
				+ ":Arguments:" + this.getArguments();
	}
	
}
