package com.example.spring02.service.admin;

import com.example.spring02.model.admin.AdminDAO;
import com.example.spring02.model.member.dto.MemberDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AdminServiceImpl implements AdminService{

    @Inject
    AdminDAO adminDao;

    @Override
    public String loginCheck(MemberDTO dto) {
        return adminDao.loginCheck(dto);
    }
}
