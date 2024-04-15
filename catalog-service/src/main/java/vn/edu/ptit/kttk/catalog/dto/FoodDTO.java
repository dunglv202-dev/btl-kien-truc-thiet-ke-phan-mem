package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.FoodType;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.entity.Image;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FoodDTO {
    private Long id;
    private String name;
    private Double price;
    private String preview;
    private String description;
    private FoodType type;
    private LocalDateTime createdAt;
    private List<String> images;

    public FoodDTO(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.price = food.getPrice();
        this.preview = food.getPreview();
        this.description = food.getDescription();
        this.type = food.getType();
        this.createdAt = food.getCreatedAt();
        this.images = food.getImages().stream().map(Image::getUrl).toList();
    }
}
