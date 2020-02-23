package com.demo.webflux.services;

import com.demo.webflux.collections.Contact;
import com.demo.webflux.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Flux<Contact> getAll() {
        return this.contactRepository.findAll(Sort.by("name"));
    }

    public Mono<Contact> save(Contact contact) { return this.contactRepository.save(contact); }
}
