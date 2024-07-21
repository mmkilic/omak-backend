package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.CostingTypes;

@Converter(autoApply = true)
public class CostingTypesConverter implements AttributeConverter<CostingTypes, String>{
	
	@Override
	public String convertToDatabaseColumn(CostingTypes attribute) {
		return attribute.name();
	}
	@Override
	public CostingTypes convertToEntityAttribute(String dbData) {
		return CostingTypes.valueOf(dbData);
	}
}
