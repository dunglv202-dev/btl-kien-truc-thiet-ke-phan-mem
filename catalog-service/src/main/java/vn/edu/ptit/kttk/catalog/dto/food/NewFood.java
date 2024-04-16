package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

import java.util.List;

@Getter
@Setter
public class NewFood {
    private String name;
    private String description;
    private Double price;
    private List<MultipartFile> images;
    private DishType type;

    public SimpleFood toEntity() {
        SimpleFood simpleFood =  new SimpleFood();
        simpleFood.setName(this.name);
        simpleFood.setDescription(this.description);
        simpleFood.setPrice(this.price);
        simpleFood.setDishType(this.type);

        return simpleFood;
    }
}
