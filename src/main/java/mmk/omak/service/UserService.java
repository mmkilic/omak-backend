package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import mmk.omak.entity.User;
import mmk.omak.entity.request.UserRequest;
import mmk.omak.enums.Types;
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
		return userRepo.getUserByEmail(email).orElseThrow(() -> new BadRequestException("User with email " +email+ " not found."));
	}
	public List<User> getAll() {
		return userRepo.findAll();
	}
	public List<User> getActives() {
		return userRepo.getActives();
	}
	public List<User> getSubordinates(String managerEmail) {
		return userRepo.getSubordinates(managerEmail);
	}
	public List<User> inVerification() {
		return userRepo.inVerification();
	}
	public List<User> getManagers() {
		return userRepo.getManagers();
	}
	public List<User> getByDepartment(Types department) {
		return userRepo.getByDepartment(department);
	}
	public List<User> getUserAndManagers(int id) {
		var user = userRepo.findById(id).orElseThrow(() -> new BadRequestException("User with id " +id+ " not found."));
		var resultList = new ArrayList<User>();
		resultList.add(user);
		User manager = user.getManager();
		while(manager != null) {
			resultList.add(manager);
			manager = manager.getManager();
		}
		return resultList;
	}
	
	
	
	public User create(UserRequest userRequest) {
		if(userRequest.getPassword().length() < 6 ) 
			throw new BadRequestException("Password length is not engouh - " +userRequest.getName());
		
		if(!userRequest.getEmail().contains("@") || !userRequest.getEmail().endsWith(".com") || userRequest.getEmail().contains(" "))
			throw new RuntimeException("Incorrect mail adress");
		
		var user = new User();
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setAuthorities(userRequest.getAuthorities());
		user.setType(userRequest.getType());
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setSurname(userRequest.getSurname());
		user.setEnabled(true);
		user.setAccountNonLocked(false);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setDateCreated(LocalDateTime.now());
		if(userRequest.getManagerEmail() != null && !userRequest.getManagerEmail().isBlank())
			user.setManager(getByEmail(userRequest.getManagerEmail()));
		
		return userRepo.save(user);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.getUserByEmail(username).orElseThrow(EntityNotFoundException::new);
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
