package vn.edu.ptit.kttk.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.edu.ptit.kttk.catalog.entity.SimpleFood;

public interface SimpleFoodRepository extends JpaRepository<SimpleFood, Long>, JpaSpecificationExecutor<SimpleFood> {
}
