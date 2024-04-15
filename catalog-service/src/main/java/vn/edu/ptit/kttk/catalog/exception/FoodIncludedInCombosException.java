package vn.edu.ptit.kttk.catalog.exception;

import lombok.Getter;
import vn.edu.ptit.kttk.catalog.entity.Combo;

import java.util.List;

@Getter
public class FoodIncludedInCombosException extends RuntimeException {
    private final List<Combo> combos;

    public FoodIncludedInCombosException(List<Combo> combosForThisFood) {
        super("Food included in one or more combos");
        this.combos = combosForThisFood;
    }
}
