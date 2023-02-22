package com.spring.demo.database.interfaces;

import com.spring.demo.model.Order;

import java.util.List;

public interface IOrderDbManager {
    List<Order> getOrders() throws Exception;
}
