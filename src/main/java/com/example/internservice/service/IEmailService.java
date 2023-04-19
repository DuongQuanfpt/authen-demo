package com.example.internservice.service;

import com.example.internservice.dto.email.EmailDetails;

public interface IEmailService {
    public String sendSimpleMail(EmailDetails details);
}
