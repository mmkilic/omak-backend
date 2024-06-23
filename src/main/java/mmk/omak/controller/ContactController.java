package mmk.omak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mmk.omak.entity.Contact;
import mmk.omak.service.ContactService;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
	
	private final ContactService contactService;
	
	@GetMapping("/{id}")
	public Contact getById(@PathVariable int id) {
		return contactService.getById(id);
	}
	
	@GetMapping
	public List<Contact> getAll() {
		return contactService.getAll();
	}
	
	
	@PostMapping
	public Contact create(@RequestBody Contact contact) {
		return contactService.create(contact);
	}
	
	
	@PutMapping
	public Contact update(@RequestBody Contact contact) {
		return contactService.update(contact);
	}
	
}
