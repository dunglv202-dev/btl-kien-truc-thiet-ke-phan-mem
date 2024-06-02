package vn.edu.ptit.kttk.catalog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.kttk.catalog.model.FoodReportSpecs;
import vn.edu.ptit.kttk.catalog.model.FoodSelling;
import vn.edu.ptit.kttk.catalog.repository.OrderRepository;
import vn.edu.ptit.kttk.catalog.service.ReportService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final OrderRepository orderRepository;

    @Override
    public List<FoodSelling> getBestsellerFoods(FoodReportSpecs specs) {
        return orderRepository.getBestsellerFoods(
            specs.getType(),
            specs.getFrom(),
            specs.getTo()
        );
    }

    @Override
    public List<FoodSelling> getTopByRevenue(FoodReportSpecs specs) {
        return orderRepository.getTopFoodsByRevenue(
            specs.getType(),
            specs.getFrom(),
            specs.getTo()
        );
    }
}
