package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.entity.Image;

@Getter
@Setter
public class ImageDTO {
    private Long id;
    private String url;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
    }
}
