package com.devweb.modelvirtualbe.security.domain.model.entity;

import com.devweb.modelvirtualbe.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @Size(max = 120)
    private String password;

    @NotBlank
    @NotNull
    @Size(max = 15)
    private String firstName;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String lastName;

    private int Year;

    private String profileImage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password,String firstName,String lastName,int Year,String profileImage) {
        this.username = username;
        this.password = password;
        this.firstName=firstName;
        this.lastName=lastName;
        this.Year=Year;
        this.profileImage=profileImage;
    }
}