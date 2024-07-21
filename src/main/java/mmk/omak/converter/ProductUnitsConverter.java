package mmk.omak.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mmk.omak.enums.ProductUnits;

@Converter(autoApply = true)
public class ProductUnitsConverter implements AttributeConverter<ProductUnits, String>{
	
	@Override
	public String convertToDatabaseColumn(ProductUnits attribute) {
		return attribute.name();
	}
	@Override
	public ProductUnits convertToEntityAttribute(String dbData) {
		return ProductUnits.valueOf(dbData);
	}
}
