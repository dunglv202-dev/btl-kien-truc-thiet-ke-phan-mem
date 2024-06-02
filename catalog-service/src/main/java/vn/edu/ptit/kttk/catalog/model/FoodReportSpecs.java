package vn.edu.ptit.kttk.catalog.model;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.constant.DishType;
import vn.edu.ptit.kttk.catalog.constant.Period;

import java.time.LocalDate;

@Getter
@Setter
public class FoodReportSpecs {
    private DishType type;
    private LocalDate from;
    private LocalDate to;
}
