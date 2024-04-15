package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.kttk.catalog.dto.FoodDTO;
import vn.edu.ptit.kttk.catalog.dto.NewFood;
import vn.edu.ptit.kttk.catalog.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public void addNewFood(@ModelAttribute NewFood newFood) {
        foodService.addNewFood(newFood);
    }

    @GetMapping
    public List<FoodDTO> getAllFoods() {
        return foodService.getAllFoods();
    }
}
