package com.devweb.modelvirtualbe.security.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResource {

    private Long id;
    @NotBlank
    @Size(max = 50)
    private String username;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;

    private int Year;

    private String profileImage;

    //private List<RoleResource> roles;
}
