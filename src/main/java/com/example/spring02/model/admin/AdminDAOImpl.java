package com.example.spring02.model.admin;

import com.example.spring02.model.member.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class AdminDAOImpl implements AdminDAO{

    @Inject
    SqlSession sqlSession;

    @Override
    public String loginCheck(MemberDTO dto) {
        return sqlSession.selectOne("admin.login_check", dto);
    }
}
