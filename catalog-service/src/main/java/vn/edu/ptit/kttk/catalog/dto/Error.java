package vn.edu.ptit.kttk.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error<P> {
    private String error;
    private P payload;
}
