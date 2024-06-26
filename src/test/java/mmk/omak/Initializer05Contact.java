package mmk.omak;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import mmk.omak.entity.Contact;
import mmk.omak.entity.request.LoginRequest;
import mmk.omak.entity.response.LoginResponse;
import reactor.core.publisher.Mono;

class Initializer05Contact {
	
	private ObjectMapper mapper;
	private final String URL = "http://localhost:8080/api-crm";
	
	public static void main(String[] args) {
		new Initializer05Contact().contextLoads();
	}
	
	void contextLoads() {
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				.build();
		
		var webClient = WebClient.builder().baseUrl(URL).build();
		
		String token = requestLogin(webClient, LoginRequest.builder()
				.email("murat@gmcendustriyel.com")
				.password("crm123")
				.build()).getToken();
		
		System.out.println("token: " + token);
		
		initializer("/json/contact.json").forEach(c -> {
			c = request(webClient, token, c);
			System.out.println(String.format("%s - %s - %s", 
					c.getId(), 
					c.getEmail(), 
					c.getName()));
		});
	}
	
	private LoginResponse requestLogin(WebClient webClient, LoginRequest loginRequest) {
		return webClient
				.post()
				.uri(uriBuilder ->
				      uriBuilder.path("/auth/login").build())
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just(loginRequest), LoginRequest.class))
				.retrieve()
				.bodyToMono(LoginResponse.class)
				.block();
	}
	
	private Contact request(WebClient webClient, String token, Contact contact) {
		return webClient
				.post()
				.uri(uriBuilder ->
				      uriBuilder.path("/contacts").build())
				.headers(h -> h.setBearerAuth(token))
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just(contact), Contact.class))
				.retrieve()
				.bodyToMono(Contact.class)
				.block();
	}
	
	private List<Contact> initializer(String file) {
		TypeReference<List<Contact>> typeReference = new TypeReference<List<Contact>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			return mapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
