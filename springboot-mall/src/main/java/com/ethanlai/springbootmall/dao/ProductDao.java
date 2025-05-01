package com.ethanlai.springbootmall.dao;

import com.ethanlai.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
