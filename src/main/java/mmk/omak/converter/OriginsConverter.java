package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.Origins;

@Converter(autoApply = true)
public class OriginsConverter implements AttributeConverter<Origins, String>{
	
	@Override
	public String convertToDatabaseColumn(Origins attribute) {
		return attribute.name();
	}
	@Override
	public Origins convertToEntityAttribute(String dbData) {
		return Origins.valueOf(dbData);
	}
}
