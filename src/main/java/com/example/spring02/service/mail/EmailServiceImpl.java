package com.example.spring02.service.mail;

import com.example.spring02.model.email.EmailDTO;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;

@Service
public class EmailServiceImpl implements EmailService {

    @Inject
    JavaMailSender mailSender;

    @Override
    public void sendMail(EmailDTO dto) {
        try {
            MimeMessage msg = mailSender.createMimeMessage(); //email object
            msg.addRecipient(RecipientType.TO,
                    new InternetAddress(dto.getReceiveMail())); //receiver
            msg.addFrom(new InternetAddress[]{
                    new InternetAddress(dto.getSenderMail(), dto.getSenderName()) }); //sender
            msg.setSubject(dto.getSubject(),"utf-8"); //email subject
            msg.setText(dto.getMessage(),"utf-8"); //email content
            mailSender.send(msg); //send email.
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
