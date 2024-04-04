package mmk.omak.enums;

public enum Origins {

	NONE("None"),
	IMPORT("Import"),
	LOCAL("Local");
	
	private final String DATA;
	
	private Origins(String data) {
		this.DATA = data;
	}
	
	public String getData() {
		return this.DATA;
	}
	
}
