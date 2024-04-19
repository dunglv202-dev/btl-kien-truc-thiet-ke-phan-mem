package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@DiscriminatorValue("COMBO")
public class Combo extends Food {
    @OneToMany(mappedBy = ComboPart_.COMBO)
    private List<ComboPart> parts;

    public boolean contains(SimpleFood food) {
        return this.parts
            .stream()
            .anyMatch(part -> Objects.equals(part.getFood().getId(), food.getId()));
    }
}
