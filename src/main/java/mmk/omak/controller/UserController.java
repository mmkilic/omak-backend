package mmk.omak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mmk.omak.entity.User;
import mmk.omak.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getUsers() {
		return userService.getActives();
	}
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.getAll();
	}
	@GetMapping("/subordinates")
	public List<User> getSubordinates(@RequestParam String managerEmail) {
		return userService.getSubordinates(managerEmail);
	}
	@GetMapping(path = "/verification")
	public List<User> inVerification() {
		return userService.inVerification();
	}
}
