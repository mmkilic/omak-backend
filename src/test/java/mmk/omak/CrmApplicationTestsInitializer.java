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

import mmk.omak.entity.request.UserRequest;
import mmk.omak.service.UserService;

@SpringBootTest
class CrmApplicationTestsInitializer {
	
	private ObjectMapper mapper;
	@Autowired
	private UserService userService;
	
	@Test
	void contextLoads() {
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				.build();
		
		initUser("/json/user_request.json");
	}
	
	void initUser(String file) {
		TypeReference<List<UserRequest>> typeReference = new TypeReference<List<UserRequest>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			List<UserRequest> users = mapper.readValue(inputStream, typeReference);
			for (UserRequest user : users) {
				userService.create(user);
				System.out.println("Users have been saved.");
			}
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
