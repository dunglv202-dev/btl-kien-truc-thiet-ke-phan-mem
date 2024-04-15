package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ComboImage extends Image {
    @ManyToOne(fetch = FetchType.LAZY)
    private Combo combo;
}
