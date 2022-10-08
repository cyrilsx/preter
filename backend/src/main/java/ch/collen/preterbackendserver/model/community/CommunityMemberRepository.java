package ch.collen.preterbackendserver.model.community;

import ch.collen.preterbackendserver.model.User;

import java.util.List;

public interface CommunityMemberRepository {
    boolean isMember(User borrower, List<String> collect);
}
