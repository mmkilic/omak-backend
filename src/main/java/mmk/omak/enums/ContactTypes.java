package mmk.omak.enums;

public enum ContactTypes {

	NONE("None"),
	CUSTOMER("Customer"),
	SUPPLIER("Supplier");
	
	private final String DATA;
	
	private ContactTypes(String data) {
		this.DATA = data;
	}
	
	public String getData() {
		return this.DATA;
	}
	
}
