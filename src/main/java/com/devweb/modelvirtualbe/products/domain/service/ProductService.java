package com.devweb.modelvirtualbe.products.domain.service;

import com.devweb.modelvirtualbe.products.domain.model.entity.Product;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    List<Product>getAllByShopId(Long shopId);
    Product create(Long shopId,Product product);
    Product update(Long shopId,Long id,Product product);
    ResponseEntity<?> delete(Long shopId,Long id);
}
