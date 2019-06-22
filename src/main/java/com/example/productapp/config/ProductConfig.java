package com.example.productapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example.productapp")
@PropertySource("classpath:application.properties")
public class ProductConfig {
}
