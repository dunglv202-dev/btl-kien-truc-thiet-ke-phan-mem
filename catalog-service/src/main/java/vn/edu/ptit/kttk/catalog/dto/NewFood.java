package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.entity.Food;

import java.util.List;

@Getter
@Setter
public abstract class NewFood {
    protected String name;
    protected String description;
    protected Double price;
    protected List<MultipartFile> addedImages;

    public <T extends Food> void fillAttributes(T t) {
        t.setName(this.name);
        t.setDescription(this.description);
        t.setPrice(this.price);
    }
}
