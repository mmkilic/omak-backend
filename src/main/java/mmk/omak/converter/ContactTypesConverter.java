package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.ContactTypes;

@Converter(autoApply = true)
public class ContactTypesConverter implements AttributeConverter<ContactTypes, String>{
	
	@Override
	public String convertToDatabaseColumn(ContactTypes attribute) {
		return attribute.name();
	}
	@Override
	public ContactTypes convertToEntityAttribute(String dbData) {
		return ContactTypes.valueOf(dbData);
	}
}
