package vn.edu.ptit.kttk.catalog.service;

import jakarta.validation.Valid;
import vn.edu.ptit.kttk.catalog.dto.NewCombo;

public interface ComboService {
    void addNewCombo(@Valid NewCombo newCombo);
}
