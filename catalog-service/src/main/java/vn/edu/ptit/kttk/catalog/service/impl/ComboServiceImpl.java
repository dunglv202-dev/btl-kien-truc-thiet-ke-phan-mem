package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboDTO;
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
import java.util.List;

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
}
