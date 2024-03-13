package mmk.omak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {
	
	private final UserDetailsService userDetailsService; 
	private final PasswordEncoder passwordEncoder;
	
	/*@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUDM = new JdbcUserDetailsManager(dataSource);
		jdbcUDM.setUsersByUsernameQuery("select email, password, active from user where email=?");
		jdbcUDM.setAuthoritiesByUsernameQuery("select email, app_role from user where email=?");
		return jdbcUDM;
	}*/
	
	/*@Bean
    InMemoryUserDetailsManager users() {
		var rnr = User.builder().username("rnr")
				.password("{noop}rnr")
    			.roles("ASD")
                .build();
		var dev = User.builder().username("dev")
				.password("{noop}rnr")
    			.roles("DEV")
                .build();
		return new InMemoryUserDetailsManager(rnr, dev);
    }*/
	
	@Bean
    AuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder);
      return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();
    }
    
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        					.components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
        					.info(new Info()
        								.title("RnR Rest API")
        								.description("Reward and Recognition Rest API")
        								.version("1.0")
        								.contact(new Contact()
        											.name("mehmet")
        											.email( "mehmet.kilic@green-transfo.com"))
        								.license(new License()
        											.name("License of API")
        											.url("API license URL")));
    }
    
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }
}
