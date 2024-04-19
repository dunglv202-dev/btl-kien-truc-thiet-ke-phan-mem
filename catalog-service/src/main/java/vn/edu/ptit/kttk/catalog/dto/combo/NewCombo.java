package vn.edu.ptit.kttk.catalog.dto.combo;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.dto.AbstractNewFood;
import vn.edu.ptit.kttk.catalog.entity.Combo;

import java.util.List;

@Getter
@Setter
public class NewCombo extends AbstractNewFood {
    private List<ComboPartDTO> parts;

    public Combo toEntity() {
        Combo combo = new Combo();
        super.fillAttributes(combo);
        return combo;
    }
}
