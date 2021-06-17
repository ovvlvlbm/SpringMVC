package com.example.spring02.service.admin;

import com.example.spring02.model.member.dto.MemberDTO;
import org.springframework.stereotype.Service;

public interface AdminService {
    String loginCheck(MemberDTO dto);
}
