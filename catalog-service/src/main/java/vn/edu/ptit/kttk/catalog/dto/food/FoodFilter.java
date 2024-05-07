package vn.edu.ptit.kttk.catalog.dto.food;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

@Getter
@Setter
public class FoodFilter {
    private String keyword;

    public Specification<SimpleFood> toSpecification() {
        if (keyword == null) return Specification.where(null);

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
    }
}
