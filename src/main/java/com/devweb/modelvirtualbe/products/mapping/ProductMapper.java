package com.devweb.modelvirtualbe.products.mapping;

import com.devweb.modelvirtualbe.products.domain.model.entity.Product;
import com.devweb.modelvirtualbe.products.resource.CreateProductResource;
import com.devweb.modelvirtualbe.products.resource.ProductResource;
import com.devweb.modelvirtualbe.products.resource.UpdateProductResource;
import com.devweb.modelvirtualbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public ProductResource toResource(Product model){
        return mapper.map(model,ProductResource.class);
    }

    public List<ProductResource> ListToResource(List<Product> modelList){
        return mapper.mapList(modelList, ProductResource.class); }

    public Product toModel(CreateProductResource resource){
        return mapper.map(resource,Product.class);
    }

    public Product toModel(UpdateProductResource resource){
        return mapper.map(resource,Product.class);
    }
}
