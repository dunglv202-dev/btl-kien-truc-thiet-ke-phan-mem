package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.dto.food.DetailSimpleFood;
import vn.edu.ptit.kttk.catalog.dto.food.NewSimpleFood;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodDTO;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodUpdate;
import vn.edu.ptit.kttk.catalog.entity.Image;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;
import vn.edu.ptit.kttk.catalog.repository.ImageRepository;
import vn.edu.ptit.kttk.catalog.repository.SimpleFoodRepository;
import vn.edu.ptit.kttk.catalog.service.FoodService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        foodRepository.save(food);
        if (newSimpleFood.getImages() != null && !newSimpleFood.getImages().isEmpty()) {
            addImages(food, newSimpleFood.getImages());
        }
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

    @Override
    @Transactional
    public void updateFood(@Valid SimpleFoodUpdate foodUpdate) {
        SimpleFood food = foodRepository.findById(foodUpdate.getId())
            .orElseThrow();
        food.mergeWithUpdates(foodUpdate);

        // update images & preview
        if (!isPreviewInUploadedImages(foodUpdate)) {
            Optional<Image> image = food.getImages()
                .stream()
                .filter(img -> img.getUrl().equals(foodUpdate.getPreview()))
                .findFirst();

            if (image.isPresent()) {
                // update preview && remove from image gallery
                food.setPreview(foodUpdate.getPreview());
                removeImages(food, List.of(image.get().getId()));
            } else {
                throw new RuntimeException("Invalid preview");
            }
        }
        if (foodUpdate.getRemovedImageIds() != null) {
            removeImages(food, foodUpdate.getRemovedImageIds());
        }
        if (foodUpdate.getAddedImages() != null) {
            addImages(food, foodUpdate.getAddedImages());
        }
    }

    @Override
    @Transactional
    public void deleteFood(Long foodId) {
        SimpleFood food = foodRepository.findById(foodId)
                .orElseThrow();
        removeImages(food.getImages());
        storageService.deleteFile(food.getPreview());
        foodRepository.delete(food);
    }

    private boolean isPreviewInUploadedImages(SimpleFoodUpdate foodUpdate) {
        return foodUpdate.getAddedImages() != null && foodUpdate.getAddedImages()
            .stream()
            .map(MultipartFile::getOriginalFilename)
            .anyMatch(fileName -> fileName != null && fileName.equals(foodUpdate.getPreview()));
    }

    private void removeImages(List<Image> removed) {
        imageRepository.deleteAllInBatch(removed);
        removed.forEach(img -> {
            storageService.deleteFile(img.getUrl());
        });
    }

    private void removeImages(SimpleFood food, List<Long> removedImageIds) {
        List<Image> removed = imageRepository.findAllByIdInAndFood(removedImageIds, food);
        removeImages(removed);
    }

    private void addImages(SimpleFood food, List<MultipartFile> added) {
        List<Image> images = new ArrayList<>();

        added.forEach(imgFile -> {
            if (imgFile.isEmpty()) return;

            String url = storageService.saveFile(imgFile);
            if (food.getPreview() == null || food.getPreview().equals(imgFile.getOriginalFilename())) {
                food.setPreview(url);
            } else {
                Image image = new Image();
                image.setUrl(url);
                image.setFood(food);
                images.add(image);
            }
        });

        imageRepository.saveAll(images);
    }
}
