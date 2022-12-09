package com.example.cometa.repos;

import com.example.cometa.domain.Defect;
import com.example.cometa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DefectRepo extends JpaRepository<Defect, Integer> {

    List<Defect> findByProduct(Product product);

    Defect findById(int id);



}
