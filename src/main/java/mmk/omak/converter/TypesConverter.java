package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.Types;

@Converter(autoApply = true)
public class TypesConverter implements AttributeConverter<Types, String>{
	
	@Override
	public String convertToDatabaseColumn(Types attribute) {
		return attribute.name();
	}
	@Override
	public Types convertToEntityAttribute(String dbData) {
		return Types.valueOf(dbData);
	}
}
