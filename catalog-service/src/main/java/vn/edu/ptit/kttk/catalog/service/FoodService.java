package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.food.DetailSimpleFood;
import vn.edu.ptit.kttk.catalog.dto.food.NewSimpleFood;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodDTO;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodUpdate;

import java.util.List;

public interface FoodService {
    void addFood(@Valid NewSimpleFood newSimpleFood);
    List<SimpleFoodDTO> getAllFoods();
    DetailSimpleFood getDetailFood(Long foodId);
    void updateFood(@Valid SimpleFoodUpdate foodUpdate);
    void deleteFood(Long foodId);
}
