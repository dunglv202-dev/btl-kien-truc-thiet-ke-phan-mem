package vn.edu.ptit.kttk.catalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdatedCombo {
    private Long id;
    private String name;
    private String preview;
    private Double price;
    private List<MultipartFile> addedImages = new ArrayList<>();
    private List<String> removedImages = new ArrayList<>();
    private List<ComboPartDTO> parts = new ArrayList<>();
}
