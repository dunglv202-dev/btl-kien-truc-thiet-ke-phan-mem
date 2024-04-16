package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.dto.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.FoodDTO;
import vn.edu.ptit.kttk.catalog.dto.NewFood;
import vn.edu.ptit.kttk.catalog.dto.UpdatedFood;
import vn.edu.ptit.kttk.catalog.entity.Combo;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.entity.FoodImage;
import vn.edu.ptit.kttk.catalog.exception.FoodIncludedInCombosException;
import vn.edu.ptit.kttk.catalog.repository.ComboPartRepository;
import vn.edu.ptit.kttk.catalog.repository.FoodImageRepository;
import vn.edu.ptit.kttk.catalog.repository.FoodRepository;
import vn.edu.ptit.kttk.catalog.service.FoodService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final StorageService storageService;
    private final FoodImageRepository foodImageRepository;
    private final ComboPartRepository comboPartRepository;

    @Override
    @Transactional
    public void addNewFood(@Valid NewFood newFood) {
        Food food = newFood.toEntity();

        // validate preview url
        if (isInvalidPreviewUrl(food, newFood.getImages(), newFood.getPreview())) {
            food.setPreview(null);
        }

        addFoodImages(food, newFood.getImages());

        foodRepository.save(food);
    }

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodRepository.findAll()
            .stream()
            .map(FoodDTO::new)
            .toList();
    }

    @Override
    @Transactional
    public void updateFood(@Valid UpdatedFood updatedFood) {
        Food food = foodRepository.findById(updatedFood.getId())
            .orElseThrow();

        // handle food info updates
        food.merge(updatedFood);

        removeFoodImages(food, updatedFood.getRemovedImages());

        if (!Objects.equals(food.getPreview(), updatedFood.getPreview())) {
            // validate preview url
            if (isInvalidPreviewUrl(food, updatedFood.getAddedImages(), updatedFood.getPreview())) {
                food.setPreview(null);
            } else {
                // remove from image gallery after set it as preview image
                Optional<FoodImage> previewFromImageGallery = food.getImages()
                    .stream()
                    .filter(img -> img.getUrl().equals(updatedFood.getPreview()))
                    .findFirst();

                if (previewFromImageGallery.isPresent()) {
                    FoodImage previewImage = previewFromImageGallery.get();
                    foodImageRepository.delete(previewImage);
                    food.getImages().remove(previewImage);
                }
            }
        }

        // handle food images updates
        addFoodImages(food, updatedFood.getAddedImages());

        foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long foodId) {
        Food food = foodRepository.findById(foodId)
            .orElseThrow();

        List<Combo> combosForThisFood = comboPartRepository.findByFood(food)
            .stream()
            .map(ComboPart::getCombo)
            .toList();

        if (!combosForThisFood.isEmpty()) {
            throw new FoodIncludedInCombosException(combosForThisFood);
        }

        foodRepository.delete(food);
    }

    @Override
    public FoodDTO getFood(Long foodId) {
        Food food = foodRepository.findById(foodId)
            .orElseThrow();
        return new FoodDTO(food);
    }

    /**
     * Is preview an existed images of food or newly added one
     */
    private boolean isInvalidPreviewUrl(Food food, List<MultipartFile> addedImages, String preview) {
        return food.getImages().stream().noneMatch(img -> img.getUrl().equals(preview))
            && addedImages.stream().noneMatch(img -> Objects.equals(img.getOriginalFilename(), preview));
    }

    /**
     * Set image as preview if match or add to food image gallery
     */
    private void addFoodImages(Food food, List<MultipartFile> addedImages) {
        addedImages.forEach(img -> {
            if (img.isEmpty()) return;

            String url = storageService.storeFile(img);

            if (Objects.equals(food.getPreview(), img.getOriginalFilename())) {
                food.setPreview(url);
            } else {
                food.addImage(url);
            }
        });
    }

    /**
     * Remove image from food image gallery
     */
    private void removeFoodImages(Food food, List<String> imageUrls) {
        List<FoodImage> removedImages = food.getImages()
            .stream()
            .filter(foodImage -> imageUrls.contains(foodImage.getUrl()))
            .toList();

        foodImageRepository.deleteAllInBatch(removedImages);
        removedImages.forEach(food.getImages()::remove);
    }
}
