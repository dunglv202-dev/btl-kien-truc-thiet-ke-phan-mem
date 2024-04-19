package vn.edu.ptit.kttk.catalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.dto.FoodUpdate;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodUpdate;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.entity.Image;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;
import vn.edu.ptit.kttk.catalog.repository.ImageRepository;
import vn.edu.ptit.kttk.catalog.service.FoodImageService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodImageServiceImpl implements FoodImageService {
    protected final ImageRepository imageRepository;
    protected final StorageService storageService;

    public void addImages(Food food, List<MultipartFile> added) {
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

    public void updateImagesAndPreview(Food food, FoodUpdate foodUpdate) {
        if (!isPreviewInUploadedImages(foodUpdate)) {
            Optional<Image> image = food.getImages()
                .stream()
                .filter(img -> img.getUrl().equals(foodUpdate.getPreview()))
                .findFirst();

            if (image.isPresent()) {
                // update preview && remove from image gallery
                food.setPreview(foodUpdate.getPreview());
                removeImages(food, List.of(image.get().getId()));
            } else if (!Objects.equals(foodUpdate.getPreview(), food.getPreview())) {
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

    public void removeImages(List<Image> removed) {
        imageRepository.deleteAllInBatch(removed);
        removed.forEach(img -> {
            storageService.deleteFile(img.getUrl());
        });
    }

    private boolean isPreviewInUploadedImages(FoodUpdate foodUpdate) {
        return foodUpdate.getAddedImages() != null && foodUpdate.getAddedImages()
            .stream()
            .map(MultipartFile::getOriginalFilename)
            .anyMatch(fileName -> fileName != null && fileName.equals(foodUpdate.getPreview()));
    }

    private void removeImages(Food food, List<Long> removedImageIds) {
        List<Image> removed = imageRepository.findAllByIdInAndFood(removedImageIds, food);
        removeImages(removed);
    }
}
