package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.dto.ImageDTO;
import vn.edu.ptit.kttk.catalog.entity.Image;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

import java.util.List;

@Getter
@Setter
public class DetailSimpleFood {
    private Long id;
    private String name;
    private String preview;
    private String description;
    private Double price;
    private DishType dishType;
    private List<ImageDTO> images;

    public DetailSimpleFood(SimpleFood food) {
        this.id = food.getId();
        this.name = food.getName();
        this.preview = food.getPreview();
        this.description = food.getDescription();
        this.price = food.getPrice();
        this.dishType = food.getDishType();
        this.images = food.getImages().stream().map(ImageDTO::new).toList();
    }
}
