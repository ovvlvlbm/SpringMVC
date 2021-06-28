package com.example.spring02.service.board;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    BoardDAO boardDao;

    @Transactional
    @Override
    public void create(BoardDTO dto) throws Exception {
        String title = dto.getTitle();
        String writer = dto.getWriter();
        String content = dto.getContent();

        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        writer = writer.replace("<", "&lt;");
        writer = writer.replace(">", "&gt;");
        content = content.replace("<", "&lt;");
        content = content.replace(">", "&gt;");

        title = title.replace("  ", "&nbsp&nsbp");
        writer = writer.replace("  ", "&nbsp&nsbp");
        content = content.replace("\n", "<br>");
        dto.setContent(content);

        dto.setTitle(title);
        dto.setWriter(writer);
        boardDao.create(dto);
        String[] files = dto.getFiles();
        if(files == null) return;
        for(String name : files) {
            boardDao.addAttach(name);
        }
    }

    @Override
    public BoardDTO read(int bno) throws Exception {
        return boardDao.read(bno);
    }

    @Override
    public void update(BoardDTO dto) throws Exception {
        boardDao.update(dto);
        String[] files=dto.getFiles();
        if(files==null) return;
        for(String name:files){
            boardDao.updateAttach(name, dto.getBno());
        }
    }

    @Override
    public void delete(int bno) throws Exception {
        boardDao.delete(bno);
    }

    @Override
    public List<BoardDTO> listAll(int start, int end, String search_option, String keyword) throws Exception {
        return boardDao.listAll(start, end, search_option, keyword);
    }

    @Override
    public void increaseViewcnt(int bno, HttpSession session) throws Exception {
        long update_time = 0;
        if(session.getAttribute("update_time" + bno) != null) {
            update_time = (long) session.getAttribute("update_time" + bno);
        }

        long current_time = System.currentTimeMillis();
        if(current_time - update_time > 5 * 1000) {
            boardDao.increaseViewcnt(bno);
            session.setAttribute("update_time" + bno, current_time);
        }
    }

    @Override
    public int countArticle(String search_option, String keyword) throws Exception {
        return boardDao.countArticle(search_option, keyword);
    }

    @Override
    public List<String> getAttach(int bno) {
        return boardDao.getAttach(bno);
    }

    @Override
    public void deleteFile(String fullName) {
        boardDao.deleteFile(fullName);
    }
}
