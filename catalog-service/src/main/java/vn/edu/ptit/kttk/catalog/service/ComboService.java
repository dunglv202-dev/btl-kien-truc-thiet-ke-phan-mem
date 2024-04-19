package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.combo.ComboUpdate;
import vn.edu.ptit.kttk.catalog.dto.combo.DetailCombo;
import vn.edu.ptit.kttk.catalog.dto.combo.NewCombo;

import java.util.List;

public interface ComboService {
    void addNewCombo(@Valid NewCombo newCombo);
    List<ComboDTO> getAllCombos();
    DetailCombo getCombo(Long comboId);
    void updateCombo(@Valid ComboUpdate comboUpdate);
}
