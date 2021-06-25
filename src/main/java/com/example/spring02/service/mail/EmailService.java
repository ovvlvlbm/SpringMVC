package com.example.spring02.service.mail;

import com.example.spring02.model.email.EmailDTO;

public interface EmailService {
    void sendMail(EmailDTO dto);
}
