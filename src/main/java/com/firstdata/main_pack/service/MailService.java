package com.firstdata.main_pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService{
    @Autowired
    private JavaMailSender mailSender;

    public String sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("9282dd001@smtp-brevo.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            System.out.println("Simple email sent successfully to: " + to);
            return "OK";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed";
        }
        
    }
}
