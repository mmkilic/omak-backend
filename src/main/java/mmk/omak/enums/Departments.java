package mmk.omak.enums;

public enum Departments {

	NONE(0),
	PLANT_MANAGEMENT(1),
	FINANCE(2),
	HUMAN_RESOURCES(3),
	PURCHISING(4),
	TENDERING(5),
	DIGITALIZATION(6),
	IT(7),
	QUALITY(8),
	SERVICE(9),
	SUPPLY_CHAIN(10),
	METHOD(11),
	FACILITY_MANAGEMENT(12),
	PRODUCTION(13),
	ENGINEERING(14),
	EHS(15),
	PROJECT_MANAGEMENT(16),
	RND(17),
	ALL(18);
	
	private final int CODE;
	
	private Departments(int code) {
		this.CODE = code;
	}
	
	public int getCode() {
		return this.CODE;
	}
	
}
