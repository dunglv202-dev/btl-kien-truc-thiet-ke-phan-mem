package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.edu.ptit.kttk.catalog.dto.FoodUpdate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String name;

    protected String description;

    protected String preview;

    protected Double price;

    @OneToMany(mappedBy = Image_.FOOD)
    protected List<Image> images;

    @CreationTimestamp
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    protected LocalDateTime updatedAt;

    protected void mergeWithUpdates(FoodUpdate update) {
        this.name = update.getName();
        this.description = update.getDescription();
        this.price = update.getPrice();
    }
}
