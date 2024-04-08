package vn.edu.ptit.kttk.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.repository.FoodRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class CatalogApplication implements CommandLineRunner {
	private final FoodRepository foodRepository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!foodRepository.existsById(1L)) {
			Food food = new Food();
			food.setId(1L);
			food.setName("Hmm...What is this?");
			food.setPrice(100.0);

			foodRepository.save(food);
		}
	}
}
