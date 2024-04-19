package vn.edu.ptit.kttk.catalog.dto.combo;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.dto.DetailFoodDTO;
import vn.edu.ptit.kttk.catalog.entity.Combo;

import java.util.List;

@Getter
@Setter
public class DetailCombo extends DetailFoodDTO {
    private List<ExtendedComboPart> parts;

    public DetailCombo(Combo combo) {
        super(combo);
        this.parts = combo.getParts()
            .stream()
            .map(ExtendedComboPart::new)
            .toList();
    }
}
