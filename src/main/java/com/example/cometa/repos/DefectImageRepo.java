package com.example.cometa.repos;

import com.example.cometa.domain.DefectImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefectImageRepo extends JpaRepository<DefectImage, Long> {


    List<DefectImage> findByDefectid(Integer defect_id);
}
