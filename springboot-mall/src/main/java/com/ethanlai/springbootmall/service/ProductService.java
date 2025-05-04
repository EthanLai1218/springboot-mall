package com.ethanlai.springbootmall.service;

import com.ethanlai.springbootmall.dto.ProductQueryParams;
import com.ethanlai.springbootmall.dto.ProductRequest;
import com.ethanlai.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
