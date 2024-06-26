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

import mmk.omak.entity.ProductBrand;
import mmk.omak.entity.request.LoginRequest;
import mmk.omak.entity.response.LoginResponse;
import reactor.core.publisher.Mono;

class Initializer06ProductBrand {
	
	private ObjectMapper mapper;
	private final String URL = "http://localhost:8080/api-crm";
	
	public static void main(String[] args) {
		new Initializer06ProductBrand().contextLoads();
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
		
		initializer("/json/brand.json").forEach(b -> {
			b = request(webClient, token, b);
			System.out.println(String.format("%s - %s - %s", 
					b.getId(), 
					b.getName(), 
					"-"));
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
	
	private ProductBrand request(WebClient webClient, String token, ProductBrand brand) {
		return webClient
				.post()
				.uri(uriBuilder ->
				      uriBuilder.path("/product-brands").build())
				.headers(h -> h.setBearerAuth(token))
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(Mono.just(brand), ProductBrand.class))
				.retrieve()
				.bodyToMono(ProductBrand.class)
				.block();
	}
	
	private List<ProductBrand> initializer(String file) {
		TypeReference<List<ProductBrand>> typeReference = new TypeReference<List<ProductBrand>>() {};
		InputStream inputStream = TypeReference.class.getResourceAsStream(file);
		try {
			return mapper.readValue(inputStream, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
