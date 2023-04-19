package com.example.internservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.internservice.dto.email.EmailDetails;
import com.example.internservice.service.IEmailService;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    private String sender = "quan@gmail.com";

    @Override
    public String sendSimpleMail(EmailDetails details) {

        // Creating a simple mail message
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());

        // Sending the mail
        mailSender.send(mailMessage);
        return "Mail Sent Successfully...";

    }


}
