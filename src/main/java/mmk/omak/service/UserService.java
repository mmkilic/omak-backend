package mmk.omak.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import mmk.omak.entity.User;
import mmk.omak.repostory.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private UserRepository userRepo;
	
	private String message = "";
	private String trace = "";
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getUsersAll() {
		return userRepo.findAll();
	}
	public List<User> getActiveUsers() {
		return userRepo.getActiveUsers();
	}
	


	public List<User> getSubordinates(String managerEmail) {
		return userRepo.getSubordinates(managerEmail);
	}
	public List<User> inVerification() {
		return userRepo.inVerification();
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
