package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("COMBO")
public class Combo extends Food {
    @OneToMany(mappedBy = ComboPart_.COMBO)
    private List<ComboPart> parts;
}
