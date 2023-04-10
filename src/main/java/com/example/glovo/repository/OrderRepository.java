package com.example.glovo.repository;

import com.example.glovo.mapper.OrderRowMapper;
import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> findAll() {
        String sql = "SELECT o.id, o.date, o.cost, p.id, p.name, p.price " +
                     "FROM orders o JOIN orders_products op ON o.id = op.order_id " +
                     "JOIN products p ON op.product_id = p.id " +
                     "ORDER BY o.date DESC";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }

    public Order findById(Long id) {
        String sql = "SELECT o.id, o.date, o.cost, p.id, p.name, p.price " +
                     "FROM orders o JOIN orders_products op ON o.id = op.order_id " +
                     "JOIN products p ON op.product_id = p.id " +
                     "WHERE o.id = ?";
        Order order = jdbcTemplate.queryForObject(sql, new OrderRowMapper(), id);
        return order;
    }


    public void save(Order order) {
        String insertOrderSql = "INSERT INTO orders (date, cost) VALUES (?, ?)";
        jdbcTemplate.update(insertOrderSql, order.getDate(), order.getCost());
        String insertOrderProductsSql = "INSERT INTO orders_products (order_id, product_id) " +
                                        "SELECT ?, p.id FROM products p WHERE p.id = ?";
        for (Product product : order.getProducts()) {
            jdbcTemplate.update(insertOrderProductsSql, order.getId(), product.getId());
        }
    }

}



