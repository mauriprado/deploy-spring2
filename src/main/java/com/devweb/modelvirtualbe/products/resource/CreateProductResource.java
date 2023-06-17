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

public class CreateProductResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;
    private Float price;
    private String image;
    //private ShopResource shop;

}
