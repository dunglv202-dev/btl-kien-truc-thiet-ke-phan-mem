package vn.edu.ptit.kttk.catalog.dto.combo;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.dto.FoodDTO;
import vn.edu.ptit.kttk.catalog.entity.Combo;

import java.util.List;

@Getter
@Setter
public class ComboDTO extends FoodDTO {
    private List<ExtendedComboPartDTO> parts;

    public ComboDTO(Combo combo) {
        super(combo);
        this.parts = combo.getParts()
            .stream()
            .map(ExtendedComboPartDTO::new)
            .toList();
    }
}
