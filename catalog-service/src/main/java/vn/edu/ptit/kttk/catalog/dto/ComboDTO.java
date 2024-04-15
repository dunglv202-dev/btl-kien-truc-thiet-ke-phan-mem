package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.entity.Combo;
import vn.edu.ptit.kttk.catalog.entity.Image;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ComboDTO {
    private Long id;
    private String name;
    private String preview;
    private List<ComboPartDTO> comboParts;
    private Double price;
    private LocalDateTime createdAt;
    private List<String> images;

    public ComboDTO(Combo combo) {
        this.id = combo.getId();
        this.name = combo.getName();
        this.preview = combo.getPreview();
        this.comboParts = combo.getComboParts().stream().map(ComboPartDTO::new).toList();
        this.createdAt = combo.getCreatedAt();
        this.images = combo.getImages().stream().map(Image::getUrl).toList();
    }
}
