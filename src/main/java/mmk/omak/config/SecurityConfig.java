package mmk.omak.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import mmk.omak.auth.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private static final String[] WHITE_LIST_URL = {"/auth/**",
			"/enums/regions",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
			.csrf(AbstractHttpConfigurer::disable)
         	.authorizeHttpRequests(auth -> 
         		auth.requestMatchers(WHITE_LIST_URL).permitAll()
         			.anyRequest().authenticated())
         	.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
         	.authenticationProvider(authenticationProvider)
         	.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.logout(logout ->
		logout.logoutUrl("/auth/logout")
			.addLogoutHandler(logoutHandler)
			.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
		
         return http.build();
    }
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}
