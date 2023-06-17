package com.devweb.modelvirtualbe.products.domain.service;

import com.devweb.modelvirtualbe.products.domain.model.entity.Favorite;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getAll();
    Favorite getById(Long id);
    List<Favorite> getFavoriteByUserId(Long userId);
    Favorite create(Favorite favorite);
    Favorite update(Long id, Favorite favorite);
    ResponseEntity<?> delete(Long id);
}
