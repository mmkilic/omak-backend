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

import mmk.omak.entity.Contact;
import mmk.omak.service.ContactService;

@SpringBootTest
class CrmApplicationInitializer5Contact {
	
	private ObjectMapper mapper;
	@Autowired
	private ContactService service;
	
	@Test
	void contextLoads() {
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				.build();
		
		initUser("/json/contact.json");
	}
	
	void initUser(String file) {
		TypeReference<List<Contact>> typeReference = new TypeReference<List<Contact>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			List<Contact> data = mapper.readValue(inputStream, typeReference);
			for (Contact datum : data) {
				service.create(datum);
				System.out.println("Contact have been saved.");
			}
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
