package vn.edu.ptit.kttk.catalog.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Food food;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Order order;
}
