package ch.collen.preterbackendserver.model.borrow;

import ch.collen.preterbackendserver.model.item.Item;

public class BorrowBookingService {

    private final BorrowBookRepository borrowBookRepository;
    private final BorrowRequestRepository borrowRequestRepository;

    public BorrowBookingService(BorrowBookRepository borrowBookRepository, BorrowRequestRepository borrowRequestRepository) {
        this.borrowBookRepository = borrowBookRepository;
        this.borrowRequestRepository = borrowRequestRepository;
    }

    public void requestToBorrow(BorrowedRequest borrowedRequest) {
        borrowRequestRepository.add(borrowedRequest);
    }

    public boolean articleAvailable(Item itemToBorrow, DateInterval borrowedPeriod) {
        return borrowBookRepository.isItemAvailable(itemToBorrow, borrowedPeriod);
    }
}
