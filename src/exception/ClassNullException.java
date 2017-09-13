package exception;

public class ClassNullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassNullException(){
		super("Class is Null");
	}

	public ClassNullException(String string) {
		super(string);
	}
	
}
