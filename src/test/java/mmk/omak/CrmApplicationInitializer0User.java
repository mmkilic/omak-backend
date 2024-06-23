package mmk.omak;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import mmk.omak.entity.User;
import mmk.omak.entity.request.UserRequest;
import mmk.omak.repostory.UserRepository;

@SpringBootTest
class CrmApplicationInitializer0User {
	
	private ObjectMapper mapper;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void contextLoads() {
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				.build();
		
		initUser("/json/user.json");
	}
	
	void initUser(String file) {
		TypeReference<List<UserRequest>> typeReference = new TypeReference<List<UserRequest>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			List<UserRequest> users = mapper.readValue(inputStream, typeReference);
			for (UserRequest ur : users) {
				var user = new User();
				user.setEmail(ur.getEmail());
				user.setName(ur.getName());
				user.setSurname(ur.getSurname());
				user.setPassword(passwordEncoder.encode("crm123"));
				user.setAuthorities(ur.getAuthorities());
				user.setEnabled(true);
				user.setAccountNonLocked(true);
				user.setAccountNonExpired(true);
				user.setCredentialsNonExpired(true);
				user.setDateCreated(LocalDateTime.now());
				userRepo.save(user);
				System.out.println("Users have been saved.");
			}
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
