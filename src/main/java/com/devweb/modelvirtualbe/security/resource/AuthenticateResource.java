package com.devweb.modelvirtualbe.security.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticateResource {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private int Year;
    private String profileImage;

    private List<String> roles;
    private String token;

}