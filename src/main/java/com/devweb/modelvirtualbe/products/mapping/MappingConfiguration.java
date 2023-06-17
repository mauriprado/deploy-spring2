package com.devweb.modelvirtualbe.products.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("productsMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ShopMapper shopMapper() {
        return new ShopMapper();
    }

    @Bean
    public ProductMapper studentMapper(){
        return new ProductMapper();
    }

    @Bean
    public FavoriteMapper favoriteMapper(){
        return new FavoriteMapper();
    }
}
