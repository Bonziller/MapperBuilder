package exception;

public class FileNameException extends Exception {

	public FileNameException() {
		super("Filename is Null or Empty");
	}

	public FileNameException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileNameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FileNameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
