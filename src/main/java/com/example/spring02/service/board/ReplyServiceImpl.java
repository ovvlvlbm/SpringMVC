package com.example.spring02.service.board;

import com.example.spring02.model.board.dao.ReplyDAO;
import com.example.spring02.model.board.dto.ReplyDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    @Inject
    ReplyDAO replyDao;

    @Override
    public List<ReplyDTO> list(Integer bno, int start, int end, HttpSession session) {
        return null;
    }

    @Override
    public int count(int bno) {
        return 0;
    }

    @Override
    public void create(ReplyDTO dto) {

    }

    @Override
    public void update(ReplyDTO dto) {

    }

    @Override
    public void delete(Integer rno) {

    }

    @Override
    public ReplyDTO detail(int rno) {
        return null;
    }
}
