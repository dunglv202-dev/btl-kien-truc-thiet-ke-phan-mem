package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.dto.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.ComboPartDTO;
import vn.edu.ptit.kttk.catalog.dto.NewCombo;
import vn.edu.ptit.kttk.catalog.dto.UpdatedCombo;
import vn.edu.ptit.kttk.catalog.entity.*;
import vn.edu.ptit.kttk.catalog.repository.ComboImageRepository;
import vn.edu.ptit.kttk.catalog.repository.ComboPartRepository;
import vn.edu.ptit.kttk.catalog.repository.ComboRepository;
import vn.edu.ptit.kttk.catalog.repository.FoodRepository;
import vn.edu.ptit.kttk.catalog.service.ComboService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
    private final StorageService storageService;
    private final FoodRepository foodRepository;
    private final ComboRepository comboRepository;
    private final ComboPartRepository comboPartRepository;
    private final ComboImageRepository comboImageRepository;

    @Override
    @Transactional
    public void addNewCombo(@Valid NewCombo newCombo) {
        Combo combo = newCombo.toEntity();

        // initialize combo parts
        List<ComboPart> comboParts = newCombo.getParts().stream().map(part -> {
            Food food = foodRepository.findById(part.getFoodId())
                .orElseThrow();
            return ComboPart.builder()
                .food(food)
                .quantity(part.getQuantity())
                .combo(combo)
                .build();
        }).toList();
        combo.setComboParts(comboParts);

        // store images
        addComboImages(combo, newCombo.getImages());

        comboRepository.save(combo);
    }

    @Override
    public List<ComboDTO> getAllCombos() {
        return comboRepository.findAll()
            .stream()
            .map(ComboDTO::new)
            .toList();
    }

    @Override
    @Transactional
    public void updateCombo(@Valid UpdatedCombo updatedCombo) {
        Combo combo = comboRepository.findById(updatedCombo.getId())
            .orElseThrow();

        updateComboBasicInfo(combo, updatedCombo);
        updateComboParts(combo, updatedCombo);
        comboRepository.save(combo);
    }

    private void updateComboBasicInfo(Combo combo, UpdatedCombo updatedCombo) {
        combo.merge(updatedCombo);
        removeComboImages(combo, updatedCombo.getRemovedImages());

        if (!Objects.equals(combo.getPreview(), updatedCombo.getPreview())) {
            Optional<ComboImage> previewInImageGallery = combo.getImages()
                .stream()
                .filter(img -> img.getUrl().equals(combo.getPreview()))
                .findFirst();

            // validate preview
            if (previewInImageGallery.isPresent()) {
                // preview is in existed image gallery => set as preview & remove from image gallery
                ComboImage previewImage = previewInImageGallery.get();
                comboImageRepository.delete(previewImage);
                combo.getImages().remove(previewImage);
            } else if (updatedCombo.getAddedImages()
                .stream()
                .noneMatch(addedImage -> combo.getPreview().equals(addedImage.getOriginalFilename()))) {
                // preview is either in image gallery or newly added images list
                combo.setPreview(null);
            }
        }

        addComboImages(combo, updatedCombo.getAddedImages());
    }

    private void removeComboImages(Combo combo, List<String> imageUrls) {
        List<ComboImage> removedImages = combo.getImages()
            .stream()
            .filter(foodImage -> imageUrls.contains(foodImage.getUrl()))
            .toList();

        comboImageRepository.deleteAllInBatch(removedImages);
        removedImages.forEach(combo.getImages()::remove);
    }

    private void addComboImages(Combo combo, List<MultipartFile> addedImages) {
        addedImages.forEach(img -> {
            if (!img.isEmpty()) {
                String url = storageService.storeFile(img);

                if (Objects.equals(combo.getPreview(), img.getOriginalFilename())) {
                    combo.setPreview(url);
                } else {
                    combo.addImage(url);
                }
            }
        });
    }

    private void updateComboParts(Combo combo, UpdatedCombo updatedCombo) {
        handleAddAndUpdateParts(combo, updatedCombo);
        handleRemoveParts(combo, updatedCombo);
    }

    private void handleAddAndUpdateParts(Combo combo, UpdatedCombo updatedCombo) {
        updatedCombo.getParts().forEach(updatedPart -> {
            Optional<ComboPart> oldPart = combo.getComboParts()
                .stream()
                .filter(part -> part.getFood().getId().equals(updatedPart.getFoodId()))
                .findFirst();

            if (oldPart.isPresent()) {
                // if old combo contains this part -> update quantity
                oldPart.get().setQuantity(updatedPart.getQuantity());
            } else {
                // if updated part not included in old combo => add new
                ComboPart newComboPart = ComboPart.builder()
                    .food(foodRepository.findById(updatedPart.getFoodId()).orElseThrow())
                    .quantity(updatedPart.getQuantity())
                    .combo(combo)
                    .build();
                combo.getComboParts().add(newComboPart);
            }
        });
    }

    private void handleRemoveParts(Combo combo, UpdatedCombo updatedCombo) {
        List<Long> remainFoodIds = updatedCombo.getParts()
            .stream()
            .map(ComboPartDTO::getFoodId)
            .toList();
        List<ComboPart> removed = combo.getComboParts()
            .stream()
            .filter(part -> !remainFoodIds.contains(part.getFood().getId()))
            .toList();
        comboPartRepository.deleteAllInBatch(removed);
        removed.forEach(combo.getComboParts()::remove);
    }
}
