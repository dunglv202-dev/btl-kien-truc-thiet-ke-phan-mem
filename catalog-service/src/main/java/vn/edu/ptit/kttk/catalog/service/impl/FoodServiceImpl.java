package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.kttk.catalog.dto.NewFood;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.repository.FoodRepository;
import vn.edu.ptit.kttk.catalog.service.FoodService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.Objects;

@Service
@Validated
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final StorageService storageService;

    @Override
    @Transactional
    public void addNewFood(@Valid NewFood newFood) {
        Food food = newFood.toEntity();

        newFood.getImages().forEach(img -> {
            String url = storageService.storeFile(img);

            if (Objects.equals(newFood.getPreview(), img.getOriginalFilename())) {
                food.setPreview(url);
            } else {
                food.addImage(url);
            }
        });

        foodRepository.save(food);
    }
}
