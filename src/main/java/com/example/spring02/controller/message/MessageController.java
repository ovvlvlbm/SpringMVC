package com.example.spring02.controller.message;


import com.example.spring02.model.message.dto.MessageDTO;
import com.example.spring02.service.message.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/messages/*")
public class MessageController {

    @Inject
    MessageService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> addMessage(@RequestBody MessageDTO dto){

        ResponseEntity<String> entity=null;

        try {
            service.addMessage(dto);
            entity=new ResponseEntity<>("success", HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return entity;
    }
}
