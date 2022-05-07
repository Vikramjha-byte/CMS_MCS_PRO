package com.contact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.contact.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	// Fake List of Contact
	List<Contact> list = List.of(new Contact(1L, "con1@contact.com", "con1", 1311L),
			new Contact(2L, "con2@contact.com", "con2", 1311L), new Contact(1L, "con1@contact.com", "con1", 1312L),
			new Contact(1L, "con2@contact.com", "con2", 1312L), new Contact(1L, "con1@contact.com", "con1", 1313L));

	@Override
	public List<Contact> getContactOfUser(Long userId) {
		return list.stream().filter(contact -> contact.getUserId().equals(userId)).collect(Collectors.toList());
	}

}
