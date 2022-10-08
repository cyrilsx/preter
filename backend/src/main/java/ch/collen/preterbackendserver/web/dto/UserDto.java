package ch.collen.preterbackendserver.web.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserDto {
    private Long id;
    private String email;

    private String username;
    private String shortUrl;
    private String image;

}
