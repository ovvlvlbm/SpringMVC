package com.example.spring02.controller.memo;

import com.example.spring02.model.memo.dto.MemoDTO;
import com.example.spring02.service.memo.MemoService;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/memo/*")
public class MemoController {

    @Inject
    MemoService memoService;

    @RequestMapping("delete/{idx}")
    public String delete(@PathVariable int idx){
        memoService.delete(idx); //레코드 삭제
        return "redirect:/memo/list.do"; //리스트로 이동
    }

    @RequestMapping("update/{idx}")
    public String update(@PathVariable int idx, MemoDTO dto){
        memoService.update(dto); //레코드 수정
        return "redirect:/memo/list.do"; //리스트로 이동
    }

    @RequestMapping("view/{idx}")
    public ModelAndView view(@PathVariable int idx, ModelAndView mav){
        mav.setViewName("/memo/view"); //memo/view 페이지에
        mav.addObject("dto", memoService.memo_view(idx)); //memoService.memo_view(idx)을 담은 dto를 보냄
        return mav;
    }

    @RequestMapping("insert.do")
    public String insert(MemoDTO dto){
        memoService.insert(dto.getWriter(), dto.getMemo());
        return "redirect:/memo/list.do"; //리스트로 이동
    }

    @RequestMapping("list.do")
    public ModelAndView list(ModelAndView mav){
        List<MemoDTO> items = memoService.list(); //메모 목록을 리턴받음
        mav.setViewName("/memo/memo_list"); //뷰의이름 설정 views/memo/memo_list.jsp
        mav.addObject("list", items);//뷰에 출력할 데이터 저장
        return mav; //화면전환
    }
}
