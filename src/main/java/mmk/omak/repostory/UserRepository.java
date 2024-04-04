package mmk.omak.repostory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.User;
import mmk.omak.enums.Types;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u from User u where u.email=(:email)")
	Optional<User> getUserByEmail(@Param("email") String email);
	
	@Query("SELECT u.manager from User u where u.id=(:userId)")
	Optional<User> getManager(@Param("userId") int userId);
	
	@Query("SELECT u from User u where u.enabled=true")
	List<User> getActives();
	
	@Query("SELECT u from User u where u.manager.email=(:managerEmail)")
	List<User> getSubordinates(@Param("managerEmail") String managerEmail);
	
	@Query("SELECT u from User u where u.enabled=false and u.accountNonLocked=true")
	List<User> inVerification();
	
	@Query("SELECT u from User u where u.department=(:dapartment)")
	List<User> getByDepartment(@Param("dapartment") Types dapartment);
	
	@Query("SELECT u from User u where u.role!=TEAM_MEMBER and u.role!=NONE")
	List<User> getManagers();
}
