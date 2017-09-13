package enums;

public enum DaoMethodName {

	CREATE_READ_UPDATE_DELETE("CREATE_READ_UPDATE_DELETE"),
	CREATE_READ_GETALL_UPDATE_DELETE("CREATE_READ_GETALL_UPDATE_DELETE");

	private String method;

	DaoMethodName(String method) {
		this.method = method;
	}

	public String getMethodName() {
		return this.method;
	}

}
