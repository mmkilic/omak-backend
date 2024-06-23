package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.DeliveryStatus;

@Converter(autoApply = true)
public class DeliveryStatusTypesConverter implements AttributeConverter<DeliveryStatus, String>{
	
	@Override
	public String convertToDatabaseColumn(DeliveryStatus attribute) {
		return attribute.name();
	}
	@Override
	public DeliveryStatus convertToEntityAttribute(String dbData) {
		return DeliveryStatus.valueOf(dbData);
	}
}
