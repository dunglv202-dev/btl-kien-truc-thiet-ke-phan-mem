package vn.edu.ptit.kttk.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.kttk.catalog.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
