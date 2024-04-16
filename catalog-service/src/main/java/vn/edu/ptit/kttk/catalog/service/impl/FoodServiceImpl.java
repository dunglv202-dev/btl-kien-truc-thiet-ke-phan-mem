package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.kttk.catalog.dto.food.DetailSimpleFood;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodDTO;
import vn.edu.ptit.kttk.catalog.dto.food.NewSimpleFood;
import vn.edu.ptit.kttk.catalog.entity.Image;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;
import vn.edu.ptit.kttk.catalog.repository.ImageRepository;
import vn.edu.ptit.kttk.catalog.repository.SimpleFoodRepository;
import vn.edu.ptit.kttk.catalog.service.FoodService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final StorageService storageService;
    private final ImageRepository imageRepository;
    private final SimpleFoodRepository foodRepository;

    @Override
    @Transactional
    public void addFood(@Valid NewSimpleFood newSimpleFood) {
        SimpleFood food = newSimpleFood.toEntity();
        List<Image> images = new ArrayList<>();

        if (!newSimpleFood.getImages().isEmpty()) {
            newSimpleFood.getImages().forEach(imgFile -> {
                if (imgFile.isEmpty()) return;

                String url = storageService.saveFile(imgFile);
                if (food.getPreview() == null) {
                    food.setPreview(url);
                } else {
                    Image image = new Image();
                    image.setUrl(url);
                    image.setFood(food);
                    images.add(image);
                }
            });
        }

        foodRepository.save(food);
        imageRepository.saveAll(images);
    }

    @Override
    public List<SimpleFoodDTO> getAllFoods() {
        return foodRepository.findAll()
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
}
