package com.example.spring02.service.message;

import com.example.spring02.model.message.dao.MessageDAO;
import com.example.spring02.model.message.dao.PointDAO;
import com.example.spring02.model.message.dto.MessageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class MessageServiceImpl implements MessageService {

    @Inject
    MessageDAO messageDao;
    @Inject
    PointDAO pointDao;

    @Transactional
    @Override
    public void addMessage(MessageDTO dto) {
        //로그확인(공통업무)
        //핵심업무

        //메시지를 테이블에 저장
        messageDao.create(dto);
        //메시지를 발송한 회원에게 10포인트 추가
        pointDao.updatePoint(dto.getSender(),10);
    }
}
