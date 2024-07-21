package mmk.omak.enums;

public enum OfferStatus {

	OPEN("Açık"),
	REVIZED("Revize"),
	ORDERED("Sipariş Oldu"),
	CANCELED("İptal");
	
	private final String DATA;
	
	private OfferStatus(String data) {
		this.DATA = data;
	}
	
	public String getData() {
		return this.DATA;
	}
	
}
