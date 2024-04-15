package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.NewCombo;
import vn.edu.ptit.kttk.catalog.dto.UpdatedCombo;

import java.util.List;

public interface ComboService {
    void addNewCombo(@Valid NewCombo newCombo);
    List<ComboDTO> getAllCombos();
    void updateCombo(@Valid UpdatedCombo updatedCombo);
    void deleteCombo(Long comboId);
}
