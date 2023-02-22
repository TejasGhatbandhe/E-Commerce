package com.spring.demo.service.interfaces;

import com.spring.demo.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrder() throws Exception;
}
