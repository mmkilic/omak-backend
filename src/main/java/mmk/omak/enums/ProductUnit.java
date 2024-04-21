package mmk.omak.enums;

public enum ProductUnit {

	NONE("Hiçbiri"),
	QUANTITY("Adet"),
	BUNDLE("Set"),
	MESASURE("Ölçü");
	
	private final String DATA;
	
	private ProductUnit(String data) {
		this.DATA = data;
	}
	
	public String getCode() {
		return this.DATA;
	}
	
}
