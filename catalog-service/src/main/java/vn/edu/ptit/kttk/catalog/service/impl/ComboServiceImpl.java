package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.kttk.catalog.dto.NewCombo;
import vn.edu.ptit.kttk.catalog.entity.Combo;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.repository.ComboRepository;
import vn.edu.ptit.kttk.catalog.repository.FoodRepository;
import vn.edu.ptit.kttk.catalog.service.ComboService;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
    private final StorageService storageService;
    private final FoodRepository foodRepository;
    private final ComboRepository comboRepository;

    @Override
    @Transactional
    public void addNewCombo(@Valid NewCombo newCombo) {
        Combo combo = newCombo.toEntity();

        // initialize combo parts
        Set<ComboPart> comboParts = newCombo.getParts().stream().map(part -> {
            Food food = foodRepository.findById(part.getFoodId())
                .orElseThrow();
            return ComboPart.builder()
                .food(food)
                .quantity(part.getQuantity())
                .combo(combo)
                .build();
        }).collect(Collectors.toSet());
        combo.setComboParts(comboParts);

        // store images
        newCombo.getImages().forEach(img -> {
            String url = storageService.storeFile(img);

            if (Objects.equals(newCombo.getPreview(), img.getOriginalFilename())) {
                combo.setPreview(url);
            } else {
                combo.addImage(url);
            }
        });

        comboRepository.save(combo);
    }
}
