package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.constant.FoodType;
import vn.edu.ptit.kttk.catalog.entity.Food;

import java.util.HashSet;
import java.util.List;

@Getter
@Setter
public class NewFood {
    private String name;
    private Double price;
    private String preview;
    private String description;
    private List<MultipartFile> images;
    private FoodType type;

    public Food toEntity() {
        return Food.builder()
            .name(this.name)
            .price(this.price)
            .description(this.description)
            .type(this.type)
            .images(new HashSet<>())
            .build();
    }
}
