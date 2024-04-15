package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.FoodDTO;
import vn.edu.ptit.kttk.catalog.dto.NewFood;

import java.util.List;

public interface FoodService {
    void addNewFood(@Valid NewFood newFood);
    List<FoodDTO> getAllFoods();
}
