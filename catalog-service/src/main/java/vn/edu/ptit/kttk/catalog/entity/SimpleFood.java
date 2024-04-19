package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.dto.food.SimpleFoodUpdate;

@Entity
@Getter
@Setter
@DiscriminatorValue("SIMPLE")
public class SimpleFood extends Food {
    @Enumerated(EnumType.STRING)
    private DishType dishType;

    public void mergeWithUpdates(SimpleFoodUpdate update) {
        super.mergeWithUpdates(update);
        this.dishType = update.getDishType();
    }
}
