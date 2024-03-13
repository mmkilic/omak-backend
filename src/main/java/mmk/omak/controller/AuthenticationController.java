package mmk.omak.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mmk.omak.entity.User;
import mmk.omak.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authService;
	
	@GetMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authService.refreshToken(request, response);
	}
	@GetMapping("/validation")
	public ResponseEntity<User> validation(HttpServletRequest request) {
	  return ResponseEntity.ok(authService.validation(request));
	}
	
	@GetMapping("/hello")
	public String hello() {
	  return "hello";
	}
	
	/*
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
	  return authService.register(request);
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
	  return ResponseEntity.ok(authService.login(request));
	}
	
	
	@PatchMapping("/change")
    public ResponseEntity<LoginResponse> changePassword(@RequestBody ChangePasswdRequest request) {
        return ResponseEntity.ok(authService.changePassword(request));
    }
	*/
}
