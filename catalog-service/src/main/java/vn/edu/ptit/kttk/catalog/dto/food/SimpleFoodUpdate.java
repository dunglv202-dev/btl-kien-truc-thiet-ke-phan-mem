package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.dto.FoodUpdate;

@Getter
@Setter
public class SimpleFoodUpdate extends FoodUpdate {
    private DishType dishType;
}
