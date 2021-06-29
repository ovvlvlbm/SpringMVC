package com.example.spring02.controller.board;

import com.example.spring02.model.board.dto.BoardDTO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.board.Pager;
import com.example.spring02.service.board.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board/*")
public class BoardController {
    @Inject
    BoardService boardService;
    @Inject
    ReplyService replyService;

    @RequestMapping(value = "write.do", method = RequestMethod.GET)
    public String write(){
        return "board/write";
    }

    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public String insert(@ModelAttribute BoardDTO dto, HttpSession session) throws Exception{
        String writer=(String)session.getAttribute("userid");
        dto.setWriter(writer);
        boardService.create(dto);
        return "redirect:/board/list.do";
    }

    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam(defaultValue = "1") int curPage, @RequestParam(defaultValue = "all") String search_option, @RequestParam(defaultValue = "")String keyword) throws Exception{
        int count=boardService.countArticle(search_option, keyword);
        Pager pager=new Pager(count, curPage);
        int start= pager.getPageBegin();
        int end=pager.getPageEnd();
        List<BoardDTO> list=boardService.listAll(start, end, search_option, keyword);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("board/list");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("list", list);
        map.put("count", count);
        map.put("search_option", search_option);
        map.put("keyword", keyword);
        map.put("pager", pager);
        mav.addObject("map", map);
        return mav;
    }

    @RequestMapping(value = "view.do", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int bno, @RequestParam int curPage, @RequestParam String search_option, @RequestParam String keyword, HttpSession session) throws Exception {
        boardService.increaseViewcnt(bno, session);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("board/view");
        mav.addObject("dto", boardService.read(bno));
        mav.addObject("count", replyService.count(bno));
        mav.addObject("curPage", curPage);
        mav.addObject("search_option", search_option);
        mav.addObject("keyword", keyword);
        return mav;
    }

    @RequestMapping("update.do")
    public String update(@ModelAttribute BoardDTO dto) throws Exception {
        boardService.update(dto);
        return "redirect:/board/list.do";
    }

    @RequestMapping("delete.do")
    public String delete(@RequestParam int bno) throws Exception {
        boardService.delete(bno);
        return "redirect:/board/list.do";
    }

    @RequestMapping("getAttach/{bno}")
    @ResponseBody
    public List<String> getAttach(@PathVariable("bno") int bno){
        return boardService.getAttach(bno);
    }

}
