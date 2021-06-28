package com.example.spring02.service.board;

import com.example.spring02.model.board.dto.BoardDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface BoardService {
    void deleteFile(String fullName);
    List<String> getAttach(int bno);
    void create(BoardDTO dto) throws Exception;
    BoardDTO read(int bno) throws Exception;
    void update(BoardDTO dto) throws Exception;
    void delete(int bno) throws Exception;
    List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception;
    void increaseViewcnt(int bno, HttpSession session) throws Exception;
    int countArticle(String search_option, String keyword) throws Exception;
}
