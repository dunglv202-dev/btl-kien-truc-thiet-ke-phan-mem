package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.dto.DetailFoodDTO;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

@Getter
@Setter
public class DetailSimpleFood extends DetailFoodDTO {
    private DishType dishType;

    public DetailSimpleFood(SimpleFood food) {
        super(food);
        this.dishType = food.getDishType();
    }
}
