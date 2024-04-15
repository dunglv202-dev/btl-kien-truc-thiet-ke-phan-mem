package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;

@Getter
@Setter
@NoArgsConstructor
public class ComboPartDTO {
    private Long foodId;
    private String foodName;
    private Integer quantity;

    public ComboPartDTO(ComboPart comboPart) {
        this.foodId = comboPart.getFood().getId();
        this.foodName = comboPart.getFood().getName();
        this.quantity = comboPart.getQuantity();
    }
}
