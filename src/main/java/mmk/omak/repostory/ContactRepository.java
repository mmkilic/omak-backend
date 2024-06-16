package mmk.omak.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mmk.omak.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
}
