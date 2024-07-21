package mmk.omak.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authorities implements GrantedAuthority{

	NONE("None"),
	ADM("Admin"),
	OFF("Offer"),
	ORD("Order"),
	CUS("Customer"),
	SUP("Supplier"),
	PRD("Product");

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
