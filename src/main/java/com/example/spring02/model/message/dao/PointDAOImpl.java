package com.example.spring02.model.message.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PointDAOImpl implements PointDAO {
    @Inject
    SqlSession sqlSession;

    @Override
    public void updatePoint(String userid, int point) {
        Map<String,Object> map=new HashMap<>();
        map.put("userid", userid);
        map.put("point", point);
        sqlSession.update("point.updatePoint", map);
    }
}
