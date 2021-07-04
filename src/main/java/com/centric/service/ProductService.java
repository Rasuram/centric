package com.centric.service;

import com.centric.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts(String name,  Integer pageNo, Integer pageSize);

    Product save(Product product);
}
