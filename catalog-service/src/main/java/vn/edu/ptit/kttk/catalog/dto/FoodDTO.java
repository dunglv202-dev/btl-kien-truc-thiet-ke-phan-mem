package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.entity.Food;

@Getter
@Setter
public abstract class FoodDTO {
    protected Long id;
    protected String name;
    protected String preview;
    protected Double price;

    public FoodDTO(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.preview = food.getPreview();
        this.price = food.getPrice();
    }
}
