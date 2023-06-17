package com.devweb.modelvirtualbe.products.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResource {
    private Long id;
    private String name;
    private Long shopId;
    private Long price;
    private String brand;
    private String image;
    private String sizes;
    private Long idUser;
}
