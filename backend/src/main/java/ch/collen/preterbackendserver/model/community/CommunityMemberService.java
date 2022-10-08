package ch.collen.preterbackendserver.model.community;

import ch.collen.preterbackendserver.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class CommunityMemberService {

    private final CommunityMemberRepository communityMemberRepository;

    public CommunityMemberService(CommunityMemberRepository communityMemberRepository) {
        this.communityMemberRepository = communityMemberRepository;
    }

    public boolean isMember(User borrower, Collection<Community> communities) {
        return communityMemberRepository.isMember(borrower, communities.stream().map(Community::id).collect(Collectors.toList()));
    }
}
