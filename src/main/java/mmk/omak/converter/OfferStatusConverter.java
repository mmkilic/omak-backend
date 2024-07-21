package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.OfferStatus;

@Converter(autoApply = true)
public class OfferStatusConverter implements AttributeConverter<OfferStatus, String>{
	
	@Override
	public String convertToDatabaseColumn(OfferStatus attribute) {
		return attribute.name();
	}
	@Override
	public OfferStatus convertToEntityAttribute(String dbData) {
		return OfferStatus.valueOf(dbData);
	}
}
