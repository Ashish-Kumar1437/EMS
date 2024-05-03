package com.example.User.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailUtil(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendMail(String to,String text,String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("ashishv1437@gmail.com");
        mailSender.send(message);
    }
}
