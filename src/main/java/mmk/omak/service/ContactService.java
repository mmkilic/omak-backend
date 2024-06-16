package mmk.omak.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Contact;
import mmk.omak.exception.BadRequestException;
import mmk.omak.repostory.ContactRepository;

@Service
@RequiredArgsConstructor
public class ContactService {
	
	private final ContactRepository contactRepo;
	
	
	public Contact getById(int id) {
		return contactRepo.findById(id).orElseThrow(() -> new BadRequestException("Contact with id " +id+ " not found."));
	}
	
	public List<Contact> getAll() {
		return contactRepo.findAll();
	}
	
	
	public Contact create(Contact contact) {
		if(contact.getId() > 0)
			throw new BadRequestException("This service is only used for new records");
		contact.setDateCreated(LocalDateTime.now());
		return contactRepo.save(contact);
	}
	
	public Contact update(Contact contactReq) {
		var contact = getById(contactReq.getId());
		contact.update(contactReq);
		return contactRepo.save(contact);
	}
}
