package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import mmk.omak.entity.User;
import mmk.omak.entity.request.UserRequest;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	private String message = "";
	private String trace = "";
	
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	public User getById(int id) {
		return userRepo.findById(id).orElseThrow(() -> new BadRequestException("User with id " +id+ " not found."));
	}
	public User getByEmail(String email) {
		return userRepo.getByEmail(email).orElseThrow(() -> new BadRequestException("User with email " +email+ " not found."));
	}
	public List<User> getAll() {
		return userRepo.findAll();
	}
	public List<User> getActives() {
		return userRepo.getActives();
	}
	
	
	public User create(UserRequest userRequest) {
		var user = new User();
		user.update(userRequest);
		user.setEnabled(true);
		user.setAccountNonLocked(false);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setDateCreated(LocalDateTime.now());
		return userRepo.save(user);
	}
	
	public User update(User userUpdate) {
		var user = getById(userUpdate.getId());
		user.update(userUpdate);
		return userRepo.save(user);
	}
	
	public User resetPassword(User user) {
		user = getById(user.getId());
		user.setPassword(passwordEncoder.encode("crm123"));
		user.setEnabled(true);
		user.setAccountNonLocked(false);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		return userRepo.save(user);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.getByEmail(username).orElseThrow(EntityNotFoundException::new);
	}
	
	
	public String getMessage() {
		String returnMsg = message;
		message = "";
		return returnMsg;
	}
	public String getTrace() {
		String returnTrace = trace;
		trace = "";
		return returnTrace;
	}
}
