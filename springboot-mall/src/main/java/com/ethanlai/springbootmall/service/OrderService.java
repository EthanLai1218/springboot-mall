package com.ethanlai.springbootmall.service;

import com.ethanlai.springbootmall.dto.CreateOrderRequest;
import com.ethanlai.springbootmall.dto.OrderQueryParams;
import com.ethanlai.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
