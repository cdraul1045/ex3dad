package com.example.mscatalogo.service;

import com.example.mscatalogo.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> list();
    public Product save(Product Product);
    public Product update(Product Product);
    public Optional<Product> findById(Integer id);
    public void deleteById(Integer id);
}
