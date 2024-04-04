package mmk.omak.enums;

public enum Types {

	NONE("None"),
	EMPLOYEE("Employee"),
	CUSTOMER("Customer"),
	SUPPLIER("Supplier");
	
	private final String DATA;
	
	private Types(String data) {
		this.DATA = data;
	}
	
	public String getCode() {
		return this.DATA;
	}
	
}
