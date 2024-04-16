package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.kttk.catalog.dto.food.NewFood;
import vn.edu.ptit.kttk.catalog.service.FoodService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/foods")
public class FoodController {
    private final FoodService foodService;

    @PostMapping
    public void addFood(@ModelAttribute NewFood newFood) {
        foodService.addFood(newFood);
    }
}
