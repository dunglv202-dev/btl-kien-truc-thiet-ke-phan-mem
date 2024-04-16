package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.kttk.catalog.dto.food.DetailSimpleFood;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodDTO;
import vn.edu.ptit.kttk.catalog.dto.food.NewSimpleFood;
import vn.edu.ptit.kttk.catalog.service.FoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/foods")
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public void addFood(@ModelAttribute NewSimpleFood newSimpleFood) {
        foodService.addFood(newSimpleFood);
    }

    @GetMapping
    public List<SimpleFoodDTO> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{foodId}")
    public DetailSimpleFood getDetailFood(@PathVariable Long foodId) {
        return foodService.getDetailFood(foodId);
    }
}
