package com.ethanlai.springbootmall.service;

import com.ethanlai.springbootmall.dto.CreateOrderRequest;
import com.ethanlai.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
