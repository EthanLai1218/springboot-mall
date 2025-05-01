package com.ethanlai.springbootmall.service;

import com.ethanlai.springbootmall.dto.ProductRequest;
import com.ethanlai.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
}
