package com.devweb.modelvirtualbe.products.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor

public class ProductResource {
    private Long id;
    private String name;
    private Float price;
    private String image;
    private ShopResource shop;
}
