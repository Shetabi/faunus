package com.faunus.api.adapter.controller;

import com.faunus.api.core.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping("/hello")
    ResponseEntity<Message> sayHello() {
        return ResponseEntity.ok(new Message("Hello World"));
    }
}
