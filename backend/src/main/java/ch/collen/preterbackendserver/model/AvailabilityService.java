package ch.collen.preterbackendserver.model;

import ch.collen.preterbackendserver.model.borrow.BorrowBookingService;
import ch.collen.preterbackendserver.model.borrow.BorrowedRequest;
import ch.collen.preterbackendserver.model.community.CommunityMemberService;
import ch.collen.preterbackendserver.model.community.CommunityService;

public class AvailabilityService {

    private final CommunityService communityService;
    private final CommunityMemberService communityMemberService;
    private final BorrowBookingService borrowBookingService;

    public AvailabilityService(CommunityService communityService, CommunityMemberService communityMemberService, BorrowBookingService borrowBookingService) {
        this.communityService = communityService;
        this.communityMemberService = communityMemberService;
        this.borrowBookingService = borrowBookingService;
    }

    public boolean isAvailableForBorrowed(BorrowedRequest borrowedRequest) {
        return communityService.hasArticle(borrowedRequest.itemToBorrow())
                && borrowBookingService.articleAvailable(borrowedRequest.itemToBorrow(), borrowedRequest.borrowedPeriod())
                && communityMemberService.isMember(borrowedRequest.borrower(), borrowedRequest.itemToBorrow().communities());
    }
}
