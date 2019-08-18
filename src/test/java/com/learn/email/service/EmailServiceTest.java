package com.learn.email.service;

import com.learn.email.model.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
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
        mapProperties.put("host", "smtp.gmail.com");
        mapProperties.put("port", "587");
        mapProperties.put("authrname", "true");
        mapProperties.put("starttls.enable", "true");
        mapProperties.put("username", "****");
        mapProperties.put("password", "****");

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
        String[] request = {"test@gmail.com", "test@yahoo.com", "test@hotmail.com", "test@test.com"};
        String[] acutalResults = this.emailServiceImpl.getAllowedAddress(request);
        assertEquals(3, acutalResults.length);
    }

    @Test
    public void sendEmail() {
        Email email = new Email();

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title></title></head>");
        sb.append("<body>");
        sb.append("<b>Dear " + "Nikita" + "<b><br><br><br>");
        sb.append("This email testing is for <b>Nikita<b> from <b>Sabin<b><br><br><br>");
        sb.append("Thank you, <br><br>");
        sb.append("Love You");
        sb.append("</body></html>");

        email.setTo(new String[]{"****", ""});
        email.setSubject("I Love You");
        email.setMimeType(MediaType.TEXT_HTML_VALUE);
        email.setContent(sb.toString());

        this.emailServiceImpl.sendEmail(email);

    }
}
