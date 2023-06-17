package com.devweb.modelvirtualbe.products.mapping;

import com.devweb.modelvirtualbe.products.domain.model.entity.Shop;
import com.devweb.modelvirtualbe.products.resource.CreateShopResource;
import com.devweb.modelvirtualbe.products.resource.ShopResource;
import com.devweb.modelvirtualbe.products.resource.UpdateShopResource;
import com.devweb.modelvirtualbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ShopMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ShopResource toResource(Shop model) {
        return mapper.map(model, ShopResource.class);
    }

    public List<ShopResource> toResourceList(List<Shop> modelList) {
        return mapper.mapList(modelList, ShopResource.class);
    }

    public Shop toModel(ShopResource resource) {
        return mapper.map(resource, Shop.class);
    }

    public Shop toModel(CreateShopResource resource) {
        return mapper.map(resource, Shop.class);
    }

    public Shop toModel(UpdateShopResource resource) {
        return mapper.map(resource, Shop.class);
    }
}
