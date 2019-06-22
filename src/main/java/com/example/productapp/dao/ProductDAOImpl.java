package com.example.productapp.dao;

import com.example.productapp.model.Product;
import com.example.productapp.model.ProductRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final static Logger log = Logger.getLogger(ProductDAOImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getProducts() {

        return jdbcTemplate.query("SELECT * FROM products", (ResultSet rs, int rowNum) -> Product.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .currentPrice(rs.getFloat("current_price"))
                    .lastUpdate(rs.getTimestamp("last_update").toLocalDateTime())
                    .build());
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO products (name, current_price) VALUES (:name, :currentPrice)",
                new BeanPropertySqlParameterSource(productRequest), keyHolder);

        Map<String, Object> keys = requireNonNull(keyHolder.getKeys(), "The generated fields are empty!");

        return productRequest.toBuilder()
                .id((Long) keys.get("ID"))
                .lastUpdate(((Timestamp) keys.get("LAST_UPDATE")).toLocalDateTime())
                .build();
    }

}
