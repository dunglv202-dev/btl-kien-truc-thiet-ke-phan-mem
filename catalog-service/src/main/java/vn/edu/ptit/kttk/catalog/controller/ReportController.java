package vn.edu.ptit.kttk.catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.kttk.catalog.dto.FoodDTO;
import vn.edu.ptit.kttk.catalog.model.FoodReportSpecs;
import vn.edu.ptit.kttk.catalog.model.FoodSelling;
import vn.edu.ptit.kttk.catalog.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/foods/bestseller")
    public List<FoodSelling> getBestseller(FoodReportSpecs specs) {
        return reportService.getBestsellerFoods(specs);
    }

    @GetMapping("/foods/top_by_revenue")
    public List<FoodSelling> getTopByRevenue(FoodReportSpecs specs) {
        return reportService.getTopByRevenue(specs);
    }
}
