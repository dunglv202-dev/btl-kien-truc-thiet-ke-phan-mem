package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboUpdate;
import vn.edu.ptit.kttk.catalog.dto.combo.DetailCombo;
import vn.edu.ptit.kttk.catalog.dto.combo.NewCombo;
import vn.edu.ptit.kttk.catalog.service.ComboService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/combos")
@RequiredArgsConstructor
public class ComboController {
    private final ComboService comboService;

    @PostMapping
    public void createNewCombo(@ModelAttribute NewCombo newCombo) {
        comboService.addNewCombo(newCombo);
    }

    @GetMapping
    public List<ComboDTO> getAllCombos() {
        return comboService.getAllCombos();
    }

    @GetMapping("/{comboId}")
    public DetailCombo getDetailCombo(@PathVariable Long comboId) {
        return comboService.getCombo(comboId);
    }

    @PutMapping("/{comboId}")
    public void updateCombo(@PathVariable Long comboId, @ModelAttribute ComboUpdate comboUpdate) {
        comboUpdate.setId(comboId);
        comboService.updateCombo(comboUpdate);
    }
}
