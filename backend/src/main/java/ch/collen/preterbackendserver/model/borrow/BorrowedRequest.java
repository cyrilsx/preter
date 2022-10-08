package ch.collen.preterbackendserver.model.borrow;

import ch.collen.preterbackendserver.model.item.Item;
import ch.collen.preterbackendserver.model.User;

public record BorrowedRequest(User borrower, Item itemToBorrow, DateInterval borrowedPeriod) {
}
