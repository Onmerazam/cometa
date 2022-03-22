package com.example.cometa.repos;
import com.example.cometa.domain.Color;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColorRepo extends CrudRepository<Color, Integer> {
    List<Color> findByNumberColor(Integer filter);
}
