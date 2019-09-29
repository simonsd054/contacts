package com.simon.react.repositories;

import com.simon.react.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, String> {

    @Override
    void delete(Contact deleted);
}
