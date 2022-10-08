package ch.collen.preterbackendserver.model.borrow;

import java.time.LocalDate;

public record DateInterval(LocalDate from, LocalDate to) {
}
