package com.example.spring02.model.message.dao;

import com.example.spring02.model.message.dto.MessageDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MessageDAOImpl implements MessageDAO{
    @Inject
    SqlSession sqlSession;

    @Override
    public void create(MessageDTO dto) {
        sqlSession.insert("message.create", dto);
    }
}
