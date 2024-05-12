package mmk.omak.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mmk.omak.auth.JwtService;
import mmk.omak.entity.User;
import mmk.omak.entity.request.ChangePasswdRequest;
import mmk.omak.entity.request.LoginRequest;
import mmk.omak.entity.request.RegisterRequest;
import mmk.omak.entity.response.LoginResponse;
import mmk.omak.enums.Authorities;
import mmk.omak.exception.UnauthorizedUserException;
import mmk.omak.repostory.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			var user = this.userRepository.getByEmail(userEmail).orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				new ObjectMapper().writeValue(response.getOutputStream(), accessToken);
			}
		}
	}
	
	public User validation(HttpServletRequest request) {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			throw new BadCredentialsException("Token is incorrect."); 
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		User user = this.userRepository.getByEmail(userEmail).orElseThrow(() -> new BadCredentialsException("Token - User conflict occurred."));
		
		if(!jwtService.isTokenValid(refreshToken, user))
			throw new BadCredentialsException("Token is not valid.");
		
		return user;
	}
	
	public String register(RegisterRequest request) {
		if(!request.getEmail().contains("@") || !request.getEmail().endsWith(".com") || request.getEmail().contains(" "))
			throw new RuntimeException("Incorrect mail adress");
		if (request.getPassword().length() < 6) {
            throw new IllegalStateException("Password should be longer than 6 digits.");
        }
		if (!request.getPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same.");
        }
		
		var user = new User();
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setSurname(request.getSurname());
		user.setDateCreated(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEnabled(false);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(Collections.singleton(Authorities.NONE));
		userRepository.save(user);
	    
	    return "Kayıt talebiniz alınmıştır ve sistem yöneticisine onay için yönlendirilmiştir.";
	}
	
	public LoginResponse login(LoginRequest request) {
		var user = userRepository.getByEmail(request.getEmail())
				.orElseThrow(() -> new BadCredentialsException("User name or password is incorrect."));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
			throw new BadCredentialsException("User name or password is incorrect.");
		
		if(!user.isAccountNonLocked())
			throw new UnauthorizedUserException("User has been locked. Please change your password.");
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getEmail(),
					request.getPassword()
			));
		} catch (Exception e) {
			throw new BadCredentialsException("User name or password is incorrect.");
		}
		
		return LoginResponse.builder()
							.token(jwtService.generateToken(user))
							.user(user)
							.build();
	}
	
	public LoginResponse changePassword(ChangePasswdRequest request) {
        var user = userRepository.getByEmail(request.getEmail()).orElseThrow(() -> new UnauthorizedUserException("User is not available!"));
        
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            throw new IllegalStateException("Wrong password");
        
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword()))
            throw new IllegalStateException("New password should be different than current password.");
        
        if (request.getNewPassword().length() < 6)
            throw new IllegalStateException("Password should be longer than 6 digits.");
        
        if (!request.getNewPassword().equals(request.getConfirmationPassword()))
            throw new IllegalStateException("Password are not the same");
        
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setAccountNonLocked(true);
        userRepository.save(user);
        
        return login(LoginRequest.builder()
        		.email(user.getEmail())
        		.password(request.getNewPassword())
        		.build());
    }
}
