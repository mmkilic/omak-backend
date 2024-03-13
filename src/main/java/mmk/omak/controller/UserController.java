package mmk.omak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import mmk.omak.entity.User;
import mmk.omak.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@Operation(summary = "all active users")
	@GetMapping
	public List<User> getUsers() {
		return userService.getActiveUsers();
	}
	@Operation(summary = "users by manager email")
	@GetMapping("/subordinates")
	public List<User> getSubordinates(@RequestParam String managerEmail) {
		return userService.getSubordinates(managerEmail);
	}
	@Operation(summary = "with all users deactivated")
	@GetMapping("/all")
	public List<User> getUsersAll() {
		return userService.getUsersAll();
	}
	@Operation(summary = "user in verification")
	@GetMapping(path = "/verification")
	public List<User> inVerification() {
		return userService.inVerification();
	}
}
