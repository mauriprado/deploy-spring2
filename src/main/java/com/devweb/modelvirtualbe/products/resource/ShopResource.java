package com.devweb.modelvirtualbe.products.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ShopResource {
    private Long id;
    private String name;
    private String logoUrl;
}
