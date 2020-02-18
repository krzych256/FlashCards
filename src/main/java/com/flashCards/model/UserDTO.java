package com.flashCards.model;

import com.flashCards.domain.Profile;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String login;
    private String email;
    private String role;
    private Profile profile;
}
