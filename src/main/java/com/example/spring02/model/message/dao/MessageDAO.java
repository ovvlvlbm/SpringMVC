package com.example.spring02.model.message.dao;

import com.example.spring02.model.message.dto.MessageDTO;

public interface MessageDAO {
    void create(MessageDTO dto);
}
