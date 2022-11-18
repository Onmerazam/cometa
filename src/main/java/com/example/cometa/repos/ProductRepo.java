package com.example.cometa.repos;

import com.example.cometa.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    Product findById(int id);

}
