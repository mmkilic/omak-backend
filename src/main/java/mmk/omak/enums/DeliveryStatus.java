package mmk.omak.enums;

public enum DeliveryStatus {

	NONE("Seçilmedi", 0),
	ONGOING("Devam ediyor", 0),
	DELIVERED("Teslim edildi", 0),
	INVOICED("Faturalandırıldı", 1);
	
	private final String DATA;
	private final int VALUE;
	
	private DeliveryStatus(String data, int value) {
		this.DATA = data;
		this.VALUE = value;
	}
	
	public String getData() {
		return this.DATA;
	}
	public int getValue() {
		return this.VALUE;
	}
	
}
