package com.learn.email.controller;

import com.learn.email.model.Email;
import com.learn.email.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class EmailControllerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    @Test
    public void home() {
        Mockito.when(emailService.getHome()).thenReturn("Hello");
        String actual = emailController.home();
        assertEquals("Hello", actual);
    }

    @Test
    public void getHTMLContent() {
        String actual = emailController.getEmailHtmlContent("Hello");
        assertTrue(actual.contains("<html>") && actual.contains("Hello"));
    }

    @Test
    public void sendEmail() {
        Mockito.when(emailService.sendEmail(any())).thenReturn(true);
        ResponseEntity<Boolean> actualResponse = emailController.sendEmail(new Email());
        assertTrue(actualResponse.getBody());
    }
}
