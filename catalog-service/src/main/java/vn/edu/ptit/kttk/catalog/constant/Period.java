package vn.edu.ptit.kttk.catalog.constant;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public enum Period {
    LAST_3_DAYS (LocalDate.now().minusDays(3), null),
    LAST_7_DAYS (LocalDate.now().minusDays(7), null),
    LAST_30_DAYS (LocalDate.now().minusDays(30), null),
    ALL_THE_TIME (null, null);

    private final LocalDate from;
    private final LocalDate to;

    Period(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }
}
