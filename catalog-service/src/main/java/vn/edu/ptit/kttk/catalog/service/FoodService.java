package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.food.NewFood;

public interface FoodService {
    void addFood(@Valid NewFood newFood);
}
