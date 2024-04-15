package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.constant.FoodType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdatedFood {
    private Long id;
    private String name;
    private Double price;
    private String preview;
    private String description;
    private FoodType type;
    private List<String> removedImages = new ArrayList<>();
    private List<MultipartFile> addedImages = new ArrayList<>();
}
