package mmk.omak.enums;

public enum ProductUnits {

	NONE("Hiçbiri"),
	QUANTITY("Adet"),
	BUNDLE("Set"),
	MESASURE("Ölçü");
	
	private final String DATA;
	
	private ProductUnits(String data) {
		this.DATA = data;
	}
	
	public String getData() {
		return this.DATA;
	}
	
}
