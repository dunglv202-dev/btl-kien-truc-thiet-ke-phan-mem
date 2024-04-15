package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.kttk.catalog.dto.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.NewCombo;
import vn.edu.ptit.kttk.catalog.dto.UpdatedCombo;
import vn.edu.ptit.kttk.catalog.service.ComboService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/combos")
@RequiredArgsConstructor
public class ComboController {
    private final ComboService comboService;

    @PostMapping
    public void addNewCombo(@ModelAttribute NewCombo newCombo) {
        comboService.addNewCombo(newCombo);
    }

    @GetMapping
    public List<ComboDTO> getAllCombos() {
        return comboService.getAllCombos();
    }

    @PutMapping("/{comboId}")
    public void updateCombo(@ModelAttribute UpdatedCombo updatedCombo, @PathVariable Long comboId) {
        updatedCombo.setId(comboId);
        comboService.updateCombo(updatedCombo);
    }
}
