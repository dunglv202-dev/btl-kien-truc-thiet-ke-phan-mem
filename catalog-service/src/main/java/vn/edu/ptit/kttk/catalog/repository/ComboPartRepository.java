package vn.edu.ptit.kttk.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.kttk.catalog.entity.ComboPart;
import vn.edu.ptit.kttk.catalog.entity.Food;

import java.util.List;

public interface ComboPartRepository extends JpaRepository<ComboPart, Long> {
    List<ComboPart> findByFood(Food food);
}
