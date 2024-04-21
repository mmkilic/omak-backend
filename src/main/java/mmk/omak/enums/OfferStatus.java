package mmk.omak.enums;

public enum OfferStatus {

	NONE("Hiçbiri"),
	OPEN("Açık"),
	REVIZED("Revize"),
	CANCELED("İptal");
	
	private final String DATA;
	
	private OfferStatus(String data) {
		this.DATA = data;
	}
	
	public String getCode() {
		return this.DATA;
	}
	
}
