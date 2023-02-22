package com.spring.demo.mapper;

import com.spring.demo.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(final ResultSet result, final int rowNum) throws SQLException {
        final Order order = new Order();
        order.setOrderId(result.getInt("orderId"));
        order.setCustomerId(result.getInt("customerId"));
        order.setOrderDate(result.getString("orderDate"));
        order.setTotalAmount(result.getInt("totalAmount"));
        return order;
    }
}