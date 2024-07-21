package mmk.omak.enums;

public enum CostingTypes {

	INDIVIDUAL_PRICE("Tekil Fiyat"),
	LIST_PRICE("Liste Fiyatı");
	
	private final String DATA;
	
	private CostingTypes(String data) {
		this.DATA = data;
	}
	
	public String getData() {
		return this.DATA;
	}
	
}
