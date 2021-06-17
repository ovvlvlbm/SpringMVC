package com.example.spring02.service.member;

import com.example.spring02.model.member.dto.MemberDTO;

import javax.servlet.http.HttpSession;

public interface MemberService {
    String loginCheck(MemberDTO dto, HttpSession session);
    void logout(HttpSession session);
}
