package com.example.mscatalogo.repository;


import com.example.mscatalogo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
