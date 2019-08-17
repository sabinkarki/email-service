package com.learn.email.controller;

import com.learn.email.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class EmailController {

    final private  EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(produces = "application/json")
    private String home(){
        return this.emailService.getHome();
    }


}


