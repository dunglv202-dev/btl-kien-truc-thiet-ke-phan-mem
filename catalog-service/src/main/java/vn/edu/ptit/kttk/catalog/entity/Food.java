package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import vn.edu.ptit.kttk.catalog.constant.FoodType;
import vn.edu.ptit.kttk.catalog.dto.UpdatedFood;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String preview;

    private String description;

    @Enumerated
    private FoodType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = FoodImage_.FOOD, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Set<FoodImage> images;

    public void addImage(String url) {
        FoodImage image = new FoodImage();
        image.setUrl(url);
        image.setFood(this);
        this.images.add(image);
    }

    public void merge(UpdatedFood updatedFood) {
        this.name = updatedFood.getName();
        this.preview = updatedFood.getPreview();
        this.price = updatedFood.getPrice();
        this.description = updatedFood.getDescription();
        this.type = updatedFood.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
