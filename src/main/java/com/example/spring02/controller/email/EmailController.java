package com.example.spring02.controller.email;

import com.example.spring02.model.email.EmailDTO;
import com.example.spring02.service.mail.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/email/*")
public class EmailController {
    @Inject
    EmailService emailService;

    @RequestMapping("write.do")
    public String write(){
        return "/email/write"; //To write a mail.
    }

    @RequestMapping("send.do")
    public String send(@ModelAttribute EmailDTO dto, Model model){
        try {
            emailService.sendMail(dto);
            model.addAttribute("message", "Sending has been successful.");
        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Sending has been failed.");
        }
        return "/email/write";
    }
}
