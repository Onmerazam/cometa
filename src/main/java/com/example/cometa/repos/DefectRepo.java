package com.example.cometa.repos;

import com.example.cometa.domain.robcom.Defect;
import com.example.cometa.domain.robcom.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefectRepo extends JpaRepository<Defect, Integer> {

    List<Defect> findByProduct(Product product);

    Defect findById(int id);



}
