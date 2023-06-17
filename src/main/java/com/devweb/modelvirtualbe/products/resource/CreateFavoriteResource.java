package com.devweb.modelvirtualbe.products.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String name;

    @NotNull
    @NotBlank
    private Float price;

    @NotNull
    @NotBlank
    private String brand;
    private Long shopId;
    private String image;
    private String sizes;
    private Long idUser;
}
