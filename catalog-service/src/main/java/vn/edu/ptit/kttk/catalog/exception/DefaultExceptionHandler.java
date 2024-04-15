package vn.edu.ptit.kttk.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.ptit.kttk.catalog.dto.ComboDTO;
import vn.edu.ptit.kttk.catalog.dto.Error;

import java.util.List;

@RestControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(FoodIncludedInCombosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error<List<ComboDTO>> handleFoodIncludedInCombos(FoodIncludedInCombosException e) {
        return new Error<>(
            e.getMessage(),
            e.getCombos().stream()
                .map(ComboDTO::new)
                .toList()
        );
    }
}
