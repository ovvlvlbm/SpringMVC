package com.example.spring02.service.shop;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Inject
    CartDAO cartDao;

    @Override
    public List<CartDTO> cartMoney() {
        return cartDao.cartMoney();
    }

    @Override
    public void insert(CartDTO dto) {
        cartDao.insert(dto);
    }

    @Override
    public List<CartDTO> listCart(String userid) {
        return cartDao.listCart(userid);
    }

    @Override
    public void delete(int cart_id) {
        cartDao.delete(cart_id);
    }

    @Override
    public void deleteAll(String userid) {
        cartDao.deleteAll(userid);
    }

    @Override
    public int sumMoney(String userid) {
        return cartDao.sumMoney(userid);
    }

    @Override
    public void modifyCart(CartDTO dto) {
        cartDao.modifyCart(dto);
    }
}
