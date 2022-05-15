package com.example.lab14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource config = new DriverManagerDataSource();
        config.setUrl("jdbc:postgresql://localhost:5432/pr15db");
        config.setUsername("postgres");
        config.setPassword("7059314");
        config.setDriverClassName("org.postgresql.Driver");
        return config;
    }
}
