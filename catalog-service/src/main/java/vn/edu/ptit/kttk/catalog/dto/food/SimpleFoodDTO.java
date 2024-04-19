package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.dto.FoodDTO;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

@Getter
@Setter
public class SimpleFoodDTO extends FoodDTO {
    private DishType dishType;

    public SimpleFoodDTO(SimpleFood simpleFood) {
        super(simpleFood);
        this.dishType = simpleFood.getDishType();
    }
}
