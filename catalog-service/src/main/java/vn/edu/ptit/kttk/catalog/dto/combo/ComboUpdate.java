package vn.edu.ptit.kttk.catalog.dto.combo;

import lombok.Getter;
import lombok.Setter;
import vn.edu.ptit.kttk.catalog.dto.FoodUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ComboUpdate extends FoodUpdate {
    private List<ComboPartDTO> parts = new ArrayList<>();
}
