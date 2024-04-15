package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Combo combo;
}
