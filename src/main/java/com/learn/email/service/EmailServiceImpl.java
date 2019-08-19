package com.learn.email.service;

import com.learn.email.model.Email;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${learn.prd.mode}")
    private String prodMode;

    @Value("${learn.allowed.domains}")
    private String allowedDomains;

    @Value("#{${email.configuration}}")
    private Map<String, String> mapProperties;

    @Override
    public Boolean sendEmail(Email email) {
        boolean result = false;
        String crlfPattern = "\\r\\n|\\n";

        if (prodMode.equalsIgnoreCase("false")) {
            email.setTo(getAllowedAddress(email.getTo()));
            email.setCc(getAllowedAddress(email.getCc()));
        }
        Properties prop = new Properties();
        prop.put("mail.smtp.host", mapProperties.get("host"));
        prop.put("mail.smtp.port", mapProperties.get("port"));
        prop.put("mail.smtp.auth", mapProperties.get("authrname"));
        prop.put("mail.smtp.starttls.enable", mapProperties.get("starttls"));


        //  properties.put("mail.smtp.port", "name of email server");
        //Session session = Session.getInstance(prop);
        String userName = mapProperties.get("username");
        String password = mapProperties.get("password");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });

        try {
            InternetAddress[] addresses = new InternetAddress[email.getTo().length];
            for (int i = 0; i < addresses.length; i++) {
                addresses[i] = new InternetAddress(email.getTo()[i]);
            }
            Message message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            String subject = email.getSubject().replace(crlfPattern, " ");
            message.setSubject(subject);

            String content = email.getContent().replace(crlfPattern, System.lineSeparator());
            message.setContent(content, email.getMimeType());
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSentDate(new Date());

            if (StringUtils.isNotEmpty(email.getFrom())) {
                InternetAddress[] fromAddress = new InternetAddress[1];
                fromAddress[0] = new InternetAddress(email.getFrom());
                message.addFrom(fromAddress);
            }
            if (ArrayUtils.isNotEmpty(email.getCc())) {
                InternetAddress[] ccAddresses = new InternetAddress[email.getCc().length];
                for (int i = 0; i < addresses.length; i++) {
                    ccAddresses[i] = new InternetAddress(email.getCc()[i]);
                    message.setRecipients(Message.RecipientType.CC, ccAddresses);
                }
            }
            Transport.send(message);
            result = true;
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public String getHome() {
        return "Hello Friend";
    }

    @Override
    public String[] getAllowedAddress(String[] addresses) {
        List<String> result = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(addresses)) {
            List<String> allowedDomainsCollection = Arrays.asList(StringUtils.split(allowedDomains, ","));
            for (int i = 0; i < addresses.length; i++) {
                if (allowedDomainsCollection.contains(addresses[i].split("@")[1].toLowerCase()))
                    result.add(addresses[i]);
            }
        }
        return result.toArray(new String[0]);
    }
}
