package com.example.spring02.service.shop;

import com.example.spring02.model.shop.dto.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> cartMoney();
    void insert(CartDTO dto);
    List<CartDTO> listCart(String userid);
    void delete(int cart_id);
    void deleteAll(String userid);
    int sumMoney(String userid);
    void modifyCart(CartDTO dto);
}
