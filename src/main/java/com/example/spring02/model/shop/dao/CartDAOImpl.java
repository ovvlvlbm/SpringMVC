package com.example.spring02.model.shop.dao;

import com.example.spring02.model.shop.dto.CartDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO{

    @Inject
    SqlSession sqlSession;

    @Override
    public List<CartDTO> cartMoney() {
        return sqlSession.selectList("cart.cart_money");
    }

    @Override
    public void insert(CartDTO dto) {
        sqlSession.insert("cart.insert", dto);
    }

    @Override
    public List<CartDTO> listCart(String userid) {
        return sqlSession.selectList("cart.listCart", userid);
    }

    @Override
    public void delete(int cart_id) {
        sqlSession.delete("cart.delete", cart_id);
    }

    @Override
    public void deleteAll(String userid) {
        sqlSession.delete("cart.deleteAll", userid);
    }

    @Override
    public int sumMoney(String userid) {
        return sqlSession.selectOne("cart.sumMoney", userid);
    }

    @Override
    public void modifyCart(CartDTO dto) {
        sqlSession.update("cart.modify", dto);
    }
}
