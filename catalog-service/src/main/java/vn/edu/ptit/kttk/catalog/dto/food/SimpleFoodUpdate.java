package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.constant.DishType;

import java.util.List;

@Getter
@Setter
public class SimpleFoodUpdate {
    private Long id;
    private String name;
    private String preview;
    private String description;
    private Double price;
    private DishType dishType;
    private List<Long> removedImageIds;
    private List<MultipartFile> addedImages;
}
