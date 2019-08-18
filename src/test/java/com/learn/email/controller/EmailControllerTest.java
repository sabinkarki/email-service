package com.learn.email.controller;

import com.learn.email.service.EmailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class EmailControllerTest {

    @Mock
    private EmailServiceImpl emailServiceImpl;

    @InjectMocks
    private EmailController emailController;

    @Test
    public void home() {
        Mockito.when(emailServiceImpl.getHome()).thenReturn("Hello");
        String actual = emailController.home();
        assertEquals("Hello", actual);
    }
}
