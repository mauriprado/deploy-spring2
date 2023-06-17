package com.devweb.modelvirtualbe.products.mapping;

import com.devweb.modelvirtualbe.products.domain.model.entity.Favorite;
import com.devweb.modelvirtualbe.products.resource.CreateFavoriteResource;
import com.devweb.modelvirtualbe.products.resource.FavoriteResource;
import com.devweb.modelvirtualbe.products.resource.UpdateFavoriteResource;
import com.devweb.modelvirtualbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class FavoriteMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public FavoriteResource toResource(Favorite model){
        return mapper.map(model, FavoriteResource.class);
    }

    public Favorite toModel(FavoriteResource resource) {
        return mapper.map(resource, Favorite.class);
    }

    public Favorite toModel(CreateFavoriteResource resource) {
        return mapper.map(resource, Favorite.class);
    }

    public Favorite toModel(UpdateFavoriteResource resource) {
        return mapper.map(resource, Favorite.class);
    }

}
