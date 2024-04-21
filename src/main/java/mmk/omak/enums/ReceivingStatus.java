package mmk.omak.enums;

public enum ReceivingStatus {

	NONE("Seçilmedi", 0),
	ONGOING("Devam ediyor", 0),
	RECEIVED("Teslim alındı", 0),
	INVOICED("Faturalandırıldı", 1);
	
	private final String DATA;
	private final int VALUE;
	
	private ReceivingStatus(String data, int value) {
		this.DATA = data;
		this.VALUE = value;
	}
	
	public String getCode() {
		return this.DATA;
	}
	public int getValue() {
		return this.VALUE;
	}
	
}
