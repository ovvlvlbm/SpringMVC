package com.example.spring02.service.shop;

import com.example.spring02.model.shop.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> listProduct();
    ProductDTO detailProduct(int product_id);
    String fileInfo(int product_id);
    void updateProduct(ProductDTO dto);
    void deleteProduct(int product_id);
    void insertProduct(ProductDTO dto);

}
