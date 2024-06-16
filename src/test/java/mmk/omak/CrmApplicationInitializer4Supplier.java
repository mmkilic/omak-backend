package mmk.omak;

import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import mmk.omak.entity.Supplier;
import mmk.omak.service.SupplierService;

@SpringBootTest
class CrmApplicationInitializer4Supplier {
	
	private ObjectMapper mapper;
	@Autowired
	private SupplierService service;
	
	@Test
	void contextLoads() {
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				.build();
		
		initUser("/json/supplier.json");
	}
	
	void initUser(String file) {
		TypeReference<List<Supplier>> typeReference = new TypeReference<List<Supplier>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			List<Supplier> data = mapper.readValue(inputStream, typeReference);
			for (Supplier datum : data) {
				service.create(datum);
				System.out.println("Supplier have been saved.");
			}
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
