package com.ethanlai.springbootmall.dao;

import com.ethanlai.springbootmall.dto.ProductRequest;
import com.ethanlai.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
