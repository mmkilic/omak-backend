package mmk.omak.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority{

	NONE("None"),
	ACC("Access"),
	ADM("Admin"),
	OPP("Opportunity"),
	PRD("Product"),
	CUS("Customer"),
	SUP("Supplier");

    private String value;

    Authorities(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }

}
