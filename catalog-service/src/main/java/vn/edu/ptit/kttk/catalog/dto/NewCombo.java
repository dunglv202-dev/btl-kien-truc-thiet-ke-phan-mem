package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.entity.Combo;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NewCombo {
    private String name;
    private String preview;
    private List<MultipartFile> images = new ArrayList<>();
    private Double price;
    private List<NewComboPart> parts;

    public Combo toEntity() {
        return Combo.builder()
            .name(this.name)
            .price(this.price)
            .images(new ArrayList<>())
            .build();
    }
}
