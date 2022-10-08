package ch.collen.preterbackendserver.action;

import ch.collen.preterbackendserver.model.AvailabilityService;
import ch.collen.preterbackendserver.model.borrow.BorrowBookingService;
import ch.collen.preterbackendserver.model.borrow.BorrowedRequest;

public class BorrowArticle {

    private final AvailabilityService availabilityService;
    private final BorrowBookingService borrowBookingService;

    public BorrowArticle(AvailabilityService availabilityService, BorrowBookingService borrowBookingService) {
        this.availabilityService = availabilityService;
        this.borrowBookingService = borrowBookingService;
    }

    void execute(BorrowedRequest borrowedRequest) {
        if (availabilityService.isAvailableForBorrowed(borrowedRequest)) {
            borrowBookingService.requestToBorrow(borrowedRequest);
        }
    }

}
