package vn.edu.ptit.kttk.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.kttk.catalog.entity.FoodImage;

public interface FoodImageRepository extends JpaRepository<FoodImage, Long> {
}
