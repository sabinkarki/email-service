package com.learn.email.service;

import com.learn.email.model.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {


    @Override
    public Boolean sendEmail(Email email) {
        return null;
    }

    @Override
    public String getHome() {
        return "Hello Friend";
    }

}
