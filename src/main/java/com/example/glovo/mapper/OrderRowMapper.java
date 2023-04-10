package com.example.glovo.mapper;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Date date = rs.getDate("date");
        Double cost = rs.getDouble("cost");

        Order order = new Order(id, date, cost);

        Long productId = rs.getLong("p.id");
        String productName = rs.getString("p.name");
        Double productPrice = rs.getDouble("p.price");

        Product product = new Product(productId, productName, productPrice);

        order.addProduct(product);

        return order;
    }


}

