package com.example.spring02.model.admin;

import com.example.spring02.model.member.dto.MemberDTO;

public interface AdminDAO {
    String loginCheck(MemberDTO dto);
}
