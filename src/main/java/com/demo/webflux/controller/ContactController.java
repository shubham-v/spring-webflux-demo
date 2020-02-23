package com.demo.webflux.controller;

import com.demo.webflux.collections.Contact;
import com.demo.webflux.services.ContactService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public Flux<Contact> getAll() {
        return this.contactService.getAll().log();
    }

    @PostMapping("/contact")
    public Mono<Contact> save(@RequestBody Contact contact) {
        log.info("Request Body..." + new Gson().toJson(contact));
        return this.contactService.save(contact).log();
    }

    @GetMapping(value = "/stream/contacts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Contact> streamAllTweets() {
        return this.contactService.getAll();
    }

}
