package com.example.spring02.service.board;

import com.example.spring02.model.board.dto.ReplyDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ReplyService {
    List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session);
    int count(int bno);
    void create(ReplyDTO dto);
    void update(ReplyDTO dto);
    void delete(Integer rno);
    ReplyDTO detail(int rno);
}
