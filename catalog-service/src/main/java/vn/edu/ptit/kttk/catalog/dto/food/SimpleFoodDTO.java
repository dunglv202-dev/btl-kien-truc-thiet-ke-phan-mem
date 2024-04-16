package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

@Getter
@Setter
public class SimpleFoodDTO {
    private Long id;
    private String name;
    private String preview;
    private Double price;
    private DishType dishType;

    public SimpleFoodDTO(SimpleFood simpleFood) {
         this.id = simpleFood.getId();
         this.name = simpleFood.getName();
         this.preview = simpleFood.getPreview();
         this.price = simpleFood.getPrice();
         this.dishType = simpleFood.getDishType();
    }
}
