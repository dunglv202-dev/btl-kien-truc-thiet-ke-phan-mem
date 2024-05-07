package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.kttk.catalog.dto.food.*;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;
import vn.edu.ptit.kttk.catalog.repository.ImageRepository;
import vn.edu.ptit.kttk.catalog.repository.SimpleFoodRepository;
import vn.edu.ptit.kttk.catalog.service.FoodImageService;
import vn.edu.ptit.kttk.catalog.service.FoodService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodImageService foodImageService;
    private final StorageService storageService;
    private final ImageRepository imageRepository;
    private final SimpleFoodRepository foodRepository;

    @Override
    @Transactional
    public void addFood(@Valid NewSimpleFood newSimpleFood) {
        SimpleFood food = newSimpleFood.toEntity();
        foodRepository.save(food);
        if (newSimpleFood.getAddedImages() != null && !newSimpleFood.getAddedImages().isEmpty()) {
            foodImageService.addImages(food, newSimpleFood.getAddedImages());
        }
    }

    @Override
    public List<SimpleFoodDTO> getAllFoods(FoodFilter foodFilter) {
        return foodRepository.findAll(foodFilter.toSpecification())
            .stream()
            .map(SimpleFoodDTO::new)
            .toList();
    }

    @Override
    public DetailSimpleFood getDetailFood(Long foodId) {
        SimpleFood foundFood = foodRepository.findById(foodId)
            .orElseThrow();

        return new DetailSimpleFood(foundFood);
    }

    @Override
    @Transactional
    public void updateFood(@Valid SimpleFoodUpdate foodUpdate) {
        SimpleFood food = foodRepository.findById(foodUpdate.getId())
            .orElseThrow();
        food.mergeWithUpdates(foodUpdate);

        // update images & preview
        foodImageService.updateImagesAndPreview(food, foodUpdate);
        foodRepository.save(food);
    }

    @Override
    @Transactional
    public void deleteFood(Long foodId) {
        SimpleFood food = foodRepository.findById(foodId)
                .orElseThrow();
        foodImageService.removeImages(food.getImages());
        storageService.deleteFile(food.getPreview());
        foodRepository.delete(food);
    }
}
