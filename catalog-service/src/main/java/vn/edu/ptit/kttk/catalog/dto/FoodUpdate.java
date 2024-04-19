package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public abstract class FoodUpdate {
    private Long id;
    private String name;
    private String preview;
    private String description;
    private Double price;
    private List<Long> removedImageIds;
    private List<MultipartFile> addedImages;
}
