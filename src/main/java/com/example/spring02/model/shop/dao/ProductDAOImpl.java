package com.example.spring02.model.shop.dao;

import com.example.spring02.model.shop.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Inject
    SqlSession sqlSession;


    @Override
    public List<ProductDTO> listProduct() {
        return sqlSession.selectList("product.list_product");
    }

    @Override
    public ProductDTO detailProduct(int product_id) {
        return sqlSession.selectOne("product.detail_product", product_id);
    }

    @Override
    public void updateProduct(ProductDTO dto) {
        sqlSession.update("product.update_product",dto);
    }

    @Override
    public void deleteProduct(int product_id) {
        sqlSession.delete("product.delete", product_id);
    }

    @Override
    public void insertProduct(ProductDTO dto) {
        sqlSession.insert("product.insert", dto);
    }

    @Override
    public String fileinfo(int product_id) {
        return sqlSession.selectOne("product.file_info", product_id);
    }
}
