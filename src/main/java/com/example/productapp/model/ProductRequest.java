package com.example.productapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class ProductRequest extends Product {

    @Override
    @JsonIgnore
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    @JsonIgnore
    public void setLastUpdate(LocalDateTime lastUpdate) {
        super.setLastUpdate(lastUpdate);
    }

}
