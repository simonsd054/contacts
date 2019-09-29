package com.simon.react.controllers;

import com.simon.react.models.Contact;
import com.simon.react.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/contacts")
    public Iterable<Contact> contact() {
        return contactRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contacts")
    public Contact save(@RequestBody Contact contact) {
        contactRepository.save(contact);

        return contact;
    }

    @RequestMapping(method = RequestMethod.GET, value = "contacts/{id}")
    public Optional<Contact> show(@PathVariable String id) {
        return contactRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/contacts/{id}")
    public Contact update(@PathVariable String id, @RequestBody Contact contact) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        Contact contact1 = optionalContact.get();
        if(contact.getName() != null)
            contact1.setName(contact.getName());
        if(contact.getAddress() != null)
            contact1.setAddress(contact.getAddress());
        if(contact.getCity() != null)
            contact1.setCity(contact.getCity());
        if(contact.getPhone() != null)
            contact1.setPhone(contact.getPhone());
        if(contact.getEmail() != null)
            contact1.setEmail(contact.getEmail());
        contactRepository.save(contact1);
        return contact1;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/contacts/{id}")
    public String delete(@PathVariable String id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        Contact contact = optionalContact.get();
        contactRepository.delete(contact);
        return "";
    }
}
