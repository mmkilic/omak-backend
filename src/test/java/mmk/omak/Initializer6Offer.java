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

import mmk.omak.entity.Offer;
import mmk.omak.entity.request.LoginRequest;
import mmk.omak.entity.response.LoginResponse;
import reactor.core.publisher.Mono;

class Initializer6Offer {
	
	private ObjectMapper mapper;
	private final String URL = "http://localhost:8080/api-crm";
	
	public static void main(String[] args) {
		new Initializer6Offer().contextLoads();
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
		
		initOffers("/json/offer.json").forEach(offer -> {
			offer = request(webClient, token, offer);
			System.out.println(String.format("%s - %s - %s", 
					offer.getId(), 
					offer.getAmount(), 
					offer.getTotalAmount()));
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
	
	private Offer request(WebClient webClient, String token, Offer offer) {
		return webClient
				.post()
				.uri(uriBuilder ->
				      uriBuilder.path("/offers").build())
				.headers(h -> h.setBearerAuth(token))
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just(offer), Offer.class))
				.retrieve()
				.bodyToMono(Offer.class)
				.block();
	}
	
	private List<Offer> initOffers(String file) {
		TypeReference<List<Offer>> typeReference = new TypeReference<List<Offer>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			return mapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
