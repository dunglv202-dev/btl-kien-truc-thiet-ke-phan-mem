package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.repository.FoodRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodRepository foodRepository;

    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
