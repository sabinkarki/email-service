package com.learn.email.service;

import com.learn.email.model.Email;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

        if (prodMode.equalsIgnoreCase("false")) {
            email.setTo(getAllowedAddress(email.getTo()));
            email.setCc(getAllowedAddress(email.getCc()));
        }

        return result;
    }

    @Override
    public String getHome() {
        return "Hello Friend";
    }

    protected String[] getAllowedAddress(String[] addresses) {
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
