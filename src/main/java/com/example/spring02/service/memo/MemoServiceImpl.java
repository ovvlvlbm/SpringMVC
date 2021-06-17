package com.example.spring02.service.memo;

import com.example.spring02.model.memo.dao.MemoDAO;
import com.example.spring02.model.memo.dto.MemoDTO;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MemoServiceImpl implements MemoService{

    @Inject
    MemoDAO memoDao;

    @Override
    public List<MemoDTO> list() {
        return memoDao.list();
    }

    @Override
    public void insert(String writer, String memo) {
        memoDao.insert(writer, memo);
    }

    @Override
    public MemoDTO memo_view(int idx) {
        return memoDao.memo_view(idx);
    }

    @Override
    public void update(MemoDTO dto) {
        memoDao.memo_update(dto);
    }

    @Override
    public void delete(int idx) {
        memoDao.memo_delete(idx);
    }
}
