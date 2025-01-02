package com.example.nexis.config;

import com.example.nexis.servlet.UserServlet;
import com.example.nexis.servlet.ProductServlet;
import com.example.nexis.servlet.OrderServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<UserServlet> userServlet() {
        return new ServletRegistrationBean<>(new UserServlet(), "/users");
    }

    @Bean
    public ServletRegistrationBean<ProductServlet> productServlet() {
        return new ServletRegistrationBean<>(new ProductServlet(), "/products");
    }

    @Bean
    public ServletRegistrationBean<OrderServlet> orderServlet() {
        return new ServletRegistrationBean<>(new OrderServlet(), "/orders");
    }
}
