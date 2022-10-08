package ch.collen.preterbackendserver.model.borrow;

import ch.collen.preterbackendserver.model.item.Item;

public interface BorrowBookRepository {
    boolean isItemAvailable(Item itemToBorrow, DateInterval borrowedPeriod);
}
