package enums;

public enum JavaVersion {

	JAVA_1_7("JAVA_1_7")/*, JAVA_1_8("JAVA_1_8"), JAVA_1_9("JAVA_1_9")*/;

	private String version;

	JavaVersion(String version) {
		this.version = version;
	}

	public String getVersionName() {
		return this.version;
	}

	public boolean isJava_1_7() {
		return this == JavaVersion.JAVA_1_7;
	}

	/*public boolean isJava_1_8() {
		return this == JavaVersion.JAVA_1_8;
	}

	public boolean isJava_1_9() {
		return this == JavaVersion.JAVA_1_9;
	}*/

}
