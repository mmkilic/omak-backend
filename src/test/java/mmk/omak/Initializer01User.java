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

import mmk.omak.entity.request.RegisterRequest;
import reactor.core.publisher.Mono;

class Initializer01User {
	
	private ObjectMapper mapper;
	private final String URL = "http://localhost:8080/api-crm";
	
	public static void main(String[] args) {
		new Initializer01User().contextLoads();
	}
	
	void contextLoads() {
		mapper = JsonMapper.builder()
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				.build();
		
		var webClient = WebClient.builder().baseUrl(URL).build();
		
		initUsers("/json/userRegister.json").forEach(ur -> {
			requestRegister(webClient, ur);
			System.out.println(String.format("%s - %s - %s", 
					ur.getName(), 
					ur.getEmail(),
					ur.getSurname()));
		});
	}
	
	private String requestRegister(WebClient webClient, RegisterRequest request) {
		return webClient
				.post()
				.uri(uriBuilder ->
				      uriBuilder.path("/auth/register").build())
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just(request), RegisterRequest.class))
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	private List<RegisterRequest> initUsers(String file) {
		TypeReference<List<RegisterRequest>> typeReference = new TypeReference<List<RegisterRequest>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			return mapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
