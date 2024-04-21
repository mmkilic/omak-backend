package mmk.omak.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u from User u where u.email=(:email)")
	Optional<User> getByEmail(@Param("email") String email);
	
	@Query("SELECT u from User u where u.enabled=true")
	List<User> getActives();
	
	@Query("SELECT u from User u where u.enabled=false and u.accountNonLocked=true")
	List<User> inVerification();
	
}
