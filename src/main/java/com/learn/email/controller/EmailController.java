package com.learn.email.controller;

import com.learn.email.model.Email;
import com.learn.email.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    final private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping(produces = "application/json")
    public String home() {
        return this.emailService.getHome();
    }

    @PostMapping( value = "/email", produces = "application/json")
    public ResponseEntity<Boolean> sendEmail(@RequestBody Email email) {
        email.setContent(getEmailHtmlContent(email.getContent()));
        email.setMimeType(MediaType.TEXT_HTML_VALUE);
        boolean isSend = this.emailService.sendEmail(email);
        return new ResponseEntity<>(isSend, HttpStatus.OK);
    }

    public String getEmailHtmlContent(String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title></title></head>");
        sb.append("<body>");
        sb.append("<b>Dear " + "Nikita" + "<b><br><br><br>");
        sb.append("This email testing is for <b>Nikita<b> from <b>Sabin<b><br><br><br>");
        sb.append("Email Content:" + content);
        sb.append("Thank you, <br><br>");
        sb.append("Love You");
        sb.append("</body></html>");
        return sb.toString();
    }

}


