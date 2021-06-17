package com.example.spring02.service.memo;

import com.example.spring02.model.memo.dto.MemoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemoService {
    List<MemoDTO> list();
    void insert(String writer, String memo);
    MemoDTO memo_view(int idx);
    void update(MemoDTO dto);
    void delete(int idx);
}
