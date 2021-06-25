package com.example.spring02.model.board.dao;

import com.example.spring02.model.board.dto.BoardDTO;

import java.util.List;

public interface BoardDAO {
    void deleteFile(String fullName);           //delete attached files
    List<String> getAttach(int bno);            //list attached files
    void addAttach(String fullName);            //save attached files
    void updatAttach(String fullName, int bno); //update attached files
    void create(BoardDTO dto) throws Exception; //write a new writing
    BoardDTO read(int bno) throws Exception;    //read writings
    void update(BoardDTO dto) throws Exception; //update writings
    void delete(int bno) throws Exception;      //delete writings
    List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception; //list including page division, searching)
    void increaseViewcnt(int bno) throws Exception; //increase view count
    int countArticle(String search_option, String keyword) throws Exception; //records count

}
