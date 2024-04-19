package vn.edu.ptit.kttk.catalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.entity.Image;
import vn.edu.ptit.kttk.catalog.repository.ImageRepository;
import vn.edu.ptit.kttk.catalog.service.FoodImageService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.ArrayList;
import java.util.List;

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
}
