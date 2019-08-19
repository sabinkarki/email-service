# This is an example of email sending using gmail server.

## System requirement
SpringBoot
Lambok
Gmail Account

## Application runs in 8080 port and has two endpoint expose getHome(Get Method) and sendEmail(Post Method)

## Request for sendEmail

{  
   "to":[  
      "test1@gmail.com",
      "test2@gmail.com"
   ],
   "subject":"Testing",
   "content":"I am Content"
}

## Response from sendEmail
True

## Properties for email are injected from application.properties
 @Value("#{${email.configuration}}")
    private Map<String, String> mapProperties;
    
 ## In the application.properties file provide username and password in email.configuration, i.e
 email.configuration = {host: 'smtp.gmail.com', port: '587', authrname: 'true', starttls: 'true', username: '****', password: '****'}
 
## If got Authentication Error message than make you email less secureapps
## https://www.google.com/settings/security/lesssecureapps ==> For less secure

# Giving the reference in application.properties file
learn.allowed.domains = ${domains}
domains = gmail.com, yahoo.com, hotmail.com

## consists of unit test cases using mockito


