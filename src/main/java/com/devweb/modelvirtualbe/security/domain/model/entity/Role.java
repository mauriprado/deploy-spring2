package com.devweb.modelvirtualbe.security.domain.model.entity;

import com.devweb.modelvirtualbe.security.domain.model.enumeration.Roles;
import com.devweb.modelvirtualbe.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "roles")
public class Role extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;
}