package com.spring.demo.database;

import com.spring.demo.database.interfaces.IOrderDbManager;
import com.spring.demo.mapper.OrderRowMapper;
import com.spring.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;



public class OrderDbManagar implements IOrderDbManager {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> getOrders() throws Exception {
        String QUERY = "SELECT * FROM orders";
        return jdbcTemplate.query(QUERY,new OrderRowMapper());
    }
}
