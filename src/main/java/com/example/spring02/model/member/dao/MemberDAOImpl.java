package com.example.spring02.model.member.dao;

import com.example.spring02.model.member.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MemberDAOImpl implements MemberDAO{

    @Inject
    SqlSession sqlSession;

    @Override
    public String loginCheck(MemberDTO dto) {
        return sqlSession.selectOne("member.login_check",dto);
    }
}
