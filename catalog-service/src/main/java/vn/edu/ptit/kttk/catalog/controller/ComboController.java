package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.kttk.catalog.dto.NewCombo;
import vn.edu.ptit.kttk.catalog.service.ComboService;

@RestController
@RequestMapping("/api/v1/combos")
@RequiredArgsConstructor
public class ComboController {
    private final ComboService comboService;

    @PostMapping
    public void addNewCombo(@ModelAttribute NewCombo newCombo) {
        comboService.addNewCombo(newCombo);
    }
}
