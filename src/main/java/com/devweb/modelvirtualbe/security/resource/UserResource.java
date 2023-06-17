package com.devweb.modelvirtualbe.security.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResource {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private int Year;
    private String profileImage;
    private List<RoleResource> roles;
}