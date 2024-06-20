package mmk.omak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mmk.omak.entity.User;
import mmk.omak.entity.request.UserRequest;
import mmk.omak.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public User getById(@PathVariable int id) {
		return userService.getById(id);
	}
	
	@GetMapping("/email")
	public User getByEmail(@RequestParam String email) {
		return userService.getByEmail(email);
	}
	
	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}
	
	@GetMapping("/avtives")
	public List<User> getActives() {
		return userService.getActives();
	}
	
	
	@PostMapping
	public User create(@RequestBody UserRequest ur) {
		return userService.create(ur);
	}
	
	
	@PutMapping
	public User update(@RequestBody User userUpdate) {
		return userService.update(userUpdate);
	}
	
	@PutMapping("/reset-password")
	public User resetPassword(@RequestBody User user) {
		return userService.resetPassword(user);
	}
	
	@DeleteMapping("/enable")
	public User enable(@RequestBody User user) {
		return userService.enable(user);
	}
}
