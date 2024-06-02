package vn.edu.ptit.kttk.catalog.service;

import vn.edu.ptit.kttk.catalog.model.FoodReportSpecs;
import vn.edu.ptit.kttk.catalog.model.FoodSelling;

import java.util.List;

public interface ReportService {
    List<FoodSelling> getBestsellerFoods(FoodReportSpecs specs);
    List<FoodSelling> getTopByRevenue(FoodReportSpecs specs);
}
