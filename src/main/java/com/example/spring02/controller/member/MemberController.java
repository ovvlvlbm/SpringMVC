package com.example.spring02.controller.member;

import com.example.spring02.model.member.dto.MemberDTO;
import com.example.spring02.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

    @Inject
    MemberService memberService;

    @RequestMapping(value = "login.do")
    public String login(){
        return "member/login";
    }

    @RequestMapping(value = "login_check.do")
    public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session){
        String name = memberService.loginCheck(dto, session);
        ModelAndView mav = new ModelAndView();
        if(name!=null){
            mav.setViewName("home");
        }else{
            mav.setViewName("member/login");
            mav.addObject("message","error");
        }
        return mav;
    }

    @RequestMapping("logout.do")
    public ModelAndView logoug(HttpSession session, ModelAndView mav){
        memberService.logout(session);
        mav.setViewName("member/login");
        mav.addObject("message", "logout");
        return mav;
    }
}
