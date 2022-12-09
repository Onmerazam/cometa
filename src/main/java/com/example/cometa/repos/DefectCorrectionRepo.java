package com.example.cometa.repos;

import com.example.cometa.domain.DefectCorrection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectCorrectionRepo extends JpaRepository<DefectCorrection, Integer> {
    DefectCorrection findByDefectId(Integer id);
}
