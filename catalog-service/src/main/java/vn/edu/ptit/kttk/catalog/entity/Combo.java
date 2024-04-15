package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.ptit.kttk.catalog.dto.UpdatedCombo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String preview;

    @OneToMany(mappedBy = ComboPart_.COMBO, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<ComboPart> comboParts;

    private Double price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = ComboImage_.COMBO, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<ComboImage> images;

    public void addImage(String url) {
        ComboImage image = new ComboImage();
        image.setUrl(url);
        image.setCombo(this);
        this.images.add(image);
    }

    public void merge(UpdatedCombo updatedCombo) {
        this.name = updatedCombo.getName();
        this.price = updatedCombo.getPrice();
    }
}
