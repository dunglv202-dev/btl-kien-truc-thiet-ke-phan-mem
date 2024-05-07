package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.food.*;

import java.util.List;

public interface FoodService {
    void addFood(@Valid NewSimpleFood newSimpleFood);
    List<SimpleFoodDTO> getAllFoods(FoodFilter foodFilter);
    DetailSimpleFood getDetailFood(Long foodId);
    void updateFood(@Valid SimpleFoodUpdate foodUpdate);
    void deleteFood(Long foodId);
}
