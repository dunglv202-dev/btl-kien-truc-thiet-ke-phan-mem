package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.NewFood;

public interface FoodService {
    void addNewFood(@Valid NewFood newFood);
}
