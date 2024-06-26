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

import mmk.omak.entity.Opportunity;
import mmk.omak.entity.request.LoginRequest;
import mmk.omak.entity.response.LoginResponse;
import reactor.core.publisher.Mono;

class Initializer09Opportunity {
	
	private ObjectMapper mapper;
	private final String URL = "http://localhost:8080/api-crm";
	
	public static void main(String[] args) {
		new Initializer09Opportunity().contextLoads();
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
		
		initializer("/json/opportunity.json").forEach(o -> {
			o = request(webClient, token, o);
			System.out.println(String.format("%s - %s - %s", 
					o.getId(), 
					o.getOffers().get(0).getId(), 
					o.getSalesOrders().get(0).getId()));
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
	
	private Opportunity request(WebClient webClient, String token, Opportunity opportunity) {
		return webClient
				.post()
				.uri(uriBuilder ->
				      uriBuilder.path("/opportunities").build())
				.headers(h -> h.setBearerAuth(token))
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just(opportunity), Opportunity.class))
				.retrieve()
				.bodyToMono(Opportunity.class)
				.block();
	}
	
	private List<Opportunity> initializer(String file) {
		TypeReference<List<Opportunity>> typeReference = new TypeReference<List<Opportunity>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			return mapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
