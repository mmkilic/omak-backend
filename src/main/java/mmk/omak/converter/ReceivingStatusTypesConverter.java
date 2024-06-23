package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.ReceivingStatus;

@Converter(autoApply = true)
public class ReceivingStatusTypesConverter implements AttributeConverter<ReceivingStatus, String>{
	
	@Override
	public String convertToDatabaseColumn(ReceivingStatus attribute) {
		return attribute.name();
	}
	@Override
	public ReceivingStatus convertToEntityAttribute(String dbData) {
		return ReceivingStatus.valueOf(dbData);
	}
}
