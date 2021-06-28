package com.example.spring02.model.board.dao;

import com.example.spring02.model.board.dto.ReplyDTO;

import java.util.List;

public interface ReplyDAO {
    List<ReplyDTO> list(Integer bno, int start, int end);
    int count(int bno);
    void create(ReplyDTO dto);
    void update(ReplyDTO dto);
    void delete(Integer rno);
    ReplyDTO detail(int rno);
}
