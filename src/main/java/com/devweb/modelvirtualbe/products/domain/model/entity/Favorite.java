package com.devweb.modelvirtualbe.products.domain.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float price;
    private String brand;
    private Long shopId;
    private String image;
    private String sizes;
    private Long idUser;
}
