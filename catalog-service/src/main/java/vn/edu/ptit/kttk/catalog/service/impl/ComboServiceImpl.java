package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboUpdate;
import vn.edu.ptit.kttk.catalog.dto.combo.DetailCombo;
import vn.edu.ptit.kttk.catalog.dto.combo.NewCombo;
import vn.edu.ptit.kttk.catalog.entity.Combo;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;
import vn.edu.ptit.kttk.catalog.repository.ComboPartRepository;
import vn.edu.ptit.kttk.catalog.repository.ComboRepository;
import vn.edu.ptit.kttk.catalog.repository.SimpleFoodRepository;
import vn.edu.ptit.kttk.catalog.service.ComboService;
import vn.edu.ptit.kttk.catalog.service.FoodImageService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class ComboServiceImpl implements ComboService {
    private final FoodImageService foodImageService;
    private final SimpleFoodRepository foodRepository;
    private final ComboRepository comboRepository;
    private final ComboPartRepository comboPartRepository;

    @Override
    @Transactional
    public void addNewCombo(@Valid NewCombo newCombo) {
        Combo combo = newCombo.toEntity();

        combo.setParts(new ArrayList<>());
        newCombo.getParts().forEach(part -> {
            SimpleFood food = foodRepository.findById(part.getFoodId())
                .orElseThrow();
            if (!combo.contains(food)) {
                combo.getParts().add(new ComboPart(null, food, part.getQuantity(), combo));
            }
        });

        foodImageService.addImages(combo, newCombo.getImages());

        comboRepository.save(combo);
        comboPartRepository.saveAll(combo.getParts());
    }

    @Override
    public List<ComboDTO> getAllCombos() {
        return comboRepository.findAll()
            .stream()
            .map(ComboDTO::new)
            .toList();
    }

    @Override
    public DetailCombo getCombo(Long comboId) {
        Combo combo = comboRepository.findById(comboId)
            .orElseThrow();

        return new DetailCombo(combo);
    }

    @Override
    @Transactional
    public void updateCombo(@Valid ComboUpdate comboUpdate) {
        Combo combo = comboRepository.findById(comboUpdate.getId())
            .orElseThrow();
        combo.mergeWithUpdates(comboUpdate);
        foodImageService.updateImagesAndPreview(combo, comboUpdate);
        updateParts(combo, comboUpdate);

        comboRepository.save(combo);
    }

    private void updateParts(Combo combo, ComboUpdate comboUpdate) {
        List<ComboPart> initialParts = Collections.unmodifiableList(combo.getParts());

        // remove part
        List<ComboPart> toBeRemoved = new ArrayList<>();
        initialParts.forEach(part -> {
            // not in updated parts
            if (comboUpdate.getParts().stream().noneMatch(p -> part.getFood().getId().equals(p.getFoodId()))) {
                toBeRemoved.add(part);
            }
        });
        comboPartRepository.deleteAllInBatch(toBeRemoved);

        // update & add part
        List<ComboPart> toBeUpdated = new ArrayList<>();
        comboUpdate.getParts().forEach(updatedPart -> {
            Optional<ComboPart> comboPart = initialParts.stream()
                .filter(p -> p.getFood().getId().equals(updatedPart.getFoodId()))
                .findFirst();

            if (comboPart.isPresent()) {
                // in initial part => check if quantity updated
                if (comboPart.get().getQuantity().equals(updatedPart.getQuantity())) {
                    return;
                }
                comboPart.get().setQuantity(updatedPart.getQuantity());
                toBeUpdated.add(comboPart.get());
            } else {
                // not in initial part => add new
                SimpleFood food = foodRepository.findById(updatedPart.getFoodId())
                    .orElseThrow();
                toBeUpdated.add(new ComboPart(null, food, updatedPart.getQuantity(), combo));
            }
        });
        comboPartRepository.saveAll(toBeUpdated);
    }
}
