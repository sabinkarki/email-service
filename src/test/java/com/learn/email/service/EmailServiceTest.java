package com.learn.email.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailServiceImpl emailServiceImpl;


    @Before
    public void setUp() {
        Map<String, String> mapProperties = new HashMap();
        mapProperties.put("host", "mail.smtp.host");
        mapProperties.put("server", "test");

        ReflectionTestUtils.setField(emailServiceImpl, "prodMode", "false");
        ReflectionTestUtils.setField(emailServiceImpl, "mapProperties", mapProperties);
        ReflectionTestUtils.setField(emailServiceImpl, "allowedDomains", "gmail.com,yahoo.com,hotmail.com");
    }


    @Test
    public void getAllowedAddressForGivenEmptyRequest() {
        String[] acutalResults = this.emailServiceImpl.getAllowedAddress(new String[0]);
        assertArrayEquals(new String[0], acutalResults);
    }

    @Test
    public void getAllowedAddressForGivenRequest() {
        String[] request = {"test@gmail.com","test@yahoo.com","test@hotmail.com","test@test.com"};
        String[] acutalResults = this.emailServiceImpl.getAllowedAddress(request);
        assertEquals(3, acutalResults.length);
    }
}
