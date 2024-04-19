package vn.edu.ptit.kttk.catalog.dto.combo;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;

@Getter
@Setter
public class ExtendedComboPartDTO extends ComboPartDTO {
    private String foodName;

    public ExtendedComboPartDTO(ComboPart comboPart) {
        this.foodId = comboPart.getFood().getId();
        this.quantity = comboPart.getQuantity();
        this.foodName = comboPart.getFood().getName();
    }
}
