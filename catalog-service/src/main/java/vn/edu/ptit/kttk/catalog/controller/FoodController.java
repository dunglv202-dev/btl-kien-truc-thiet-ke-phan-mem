package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.kttk.catalog.dto.food.*;
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
    public List<SimpleFoodDTO> getAllFoods(FoodFilter foodFilter) {
        return foodService.getAllFoods(foodFilter);
    }

    @GetMapping("/{foodId}")
    public DetailSimpleFood getDetailFood(@PathVariable Long foodId) {
        return foodService.getDetailFood(foodId);
    }

    @PutMapping("/{foodId}")
    public void updateFood(@PathVariable Long foodId, @ModelAttribute SimpleFoodUpdate foodUpdate) {
        foodUpdate.setId(foodId);
        foodService.updateFood(foodUpdate);
    }

    @DeleteMapping("/{foodId}")
    public void deleteFood(@PathVariable Long foodId) {
        foodService.deleteFood(foodId);
    }
}
