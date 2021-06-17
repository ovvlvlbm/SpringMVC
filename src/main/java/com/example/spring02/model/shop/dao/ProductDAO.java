package com.example.spring02.model.shop.dao;

import com.example.spring02.model.shop.dto.ProductDTO;

import java.util.List;

public interface ProductDAO {

    List<ProductDTO> listProduct();
    ProductDTO detailProduct(int product_id);
    void updateProduct(ProductDTO dto);
    void deleteProduct(int product_id);
    void insertProduct(ProductDTO dto);
    String fileinfo(int product_id);

}
