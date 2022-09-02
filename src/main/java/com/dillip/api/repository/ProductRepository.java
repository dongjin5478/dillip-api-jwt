package com.dillip.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dillip.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
