package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.dto.NewFood;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

@Getter
@Setter
public class NewSimpleFood extends NewFood {
    private DishType dishType;

    public SimpleFood toEntity() {
        SimpleFood simpleFood =  new SimpleFood();
        super.fillAttributes(simpleFood);
        simpleFood.setDishType(this.dishType);

        return simpleFood;
    }
}
