package com.learn.email.service;

import com.learn.email.model.Email;

public interface EmailService {

     Boolean sendEmail(Email email);
     String getHome();
}
