package vn.edu.ptit.kttk.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.entity.Order;
import vn.edu.ptit.kttk.catalog.model.FoodSelling;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = """
        SELECT f.id, f.name, f.preview, SUM(i.quantity) AS total
        FROM `order_item` i
        INNER JOIN `order` o
            ON i.order_id = o.id
        INNER JOIN `food` f
            ON i.food_id = f.id
        WHERE f.dish_type = :#{#type.name()}
            AND (:from IS NULL OR DATE(o.created_at) >= :from)
            AND (:to IS NULL OR DATE(o.created_at) <= :to)
        GROUP BY f.id
        ORDER BY total DESC
        LIMIT 5
    """, nativeQuery = true)
    List<FoodSelling> getBestsellerFoods(
        @Param("type") DishType type,
        @Param("from") LocalDate from,
        @Param("to") LocalDate to
    );

    @Query(value = """
        SELECT f.id, f.name, f.preview, SUM(i.quantity * i.price) AS total
        FROM `order_item` i
        INNER JOIN `order` o
            ON i.order_id = o.id
        INNER JOIN `food` f
            ON i.food_id = f.id
        WHERE f.dish_type = :#{#type.name()}
            AND (:from IS NULL OR DATE(o.created_at) >= :from)
            AND (:to IS NULL OR DATE(o.created_at) <= :to)
        GROUP BY f.id
        ORDER BY total DESC
        LIMIT 5
    """, nativeQuery = true)
    List<FoodSelling> getTopFoodsByRevenue(DishType type, LocalDate from, LocalDate to);
}
