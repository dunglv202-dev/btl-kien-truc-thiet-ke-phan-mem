package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.entity.Food;

import java.util.List;

@Getter
@Setter
public class DetailFoodDTO extends FoodDTO {
    protected List<ImageDTO> images;

    public DetailFoodDTO(Food food) {
        super(food);
        this.images = food.getImages().stream().map(ImageDTO::new).toList();
    }
}
