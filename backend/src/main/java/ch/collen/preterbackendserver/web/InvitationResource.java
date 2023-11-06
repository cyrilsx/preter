package ch.collen.preterbackendserver.web;

import ch.collen.preterbackendserver.db.InvitationRepository;
import ch.collen.preterbackendserver.db.document.Invitation;
import ch.collen.preterbackendserver.web.dto.InvitationConfigurationDto;
import ch.collen.preterbackendserver.web.dto.InvitationRequestDto;
import ch.collen.preterbackendserver.web.dto.InvitationRequestId;
import ch.collen.preterbackendserver.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/invitations")
public class InvitationResource {

    private final InvitationRepository invitationRepository;

    /* FIXME configuration */
    private final AtomicReference<InvitationConfigurationDto> invitationConfigurationDto = new AtomicReference<>(new InvitationConfigurationDto());

    public InvitationResource(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @PostMapping("/request")
    public Mono<ResponseEntity<InvitationRequestId>> request(@RequestBody InvitationRequestDto invitationRequest) {
        return this.invitationRepository.save(new Invitation(invitationRequest.getEmail(), UUID.randomUUID().toString()))
                .map(invitation -> ResponseEntity.ok(new InvitationRequestId(invitation.getRandomInvitationId())));
    }


    @PostMapping("/confirm")
    public Mono<ResponseEntity<UserDto>> confirm(@RequestBody InvitationRequestId invitationRequestId) {
        return invitationRepository.deleteById(invitationRequestId.getInvitationRequestId())
                .map(invitation -> ResponseEntity.ok(new UserDto())); // FIXME
    }


    @GetMapping("/request")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<InvitationRequestDto> getInvitationRequests() {
        return invitationRepository.findAll()
                .map(invitation -> new InvitationRequestDto(invitation.getEmail()));
    }

    @PostMapping("/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<InvitationRequestDto>> acceptInvitation() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @PostMapping("/configure")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InvitationConfigurationDto> configure(@RequestBody InvitationConfigurationDto invitationConfigurationDto) {
        this.invitationConfigurationDto.set(invitationConfigurationDto);
        return ResponseEntity.ok(invitationConfigurationDto);
    }

}
