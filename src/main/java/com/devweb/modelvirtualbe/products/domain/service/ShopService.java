package com.devweb.modelvirtualbe.products.domain.service;

import com.devweb.modelvirtualbe.products.domain.model.entity.Shop;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShopService {
    List<Shop> getAll();
    Shop getById(Long id);
    Shop create(Shop shop);
    Shop update(Long id, Shop shop);
    ResponseEntity<?> delete(Long id);
}
