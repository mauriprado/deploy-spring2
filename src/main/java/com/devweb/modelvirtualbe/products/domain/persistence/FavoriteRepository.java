package com.devweb.modelvirtualbe.products.domain.persistence;

import com.devweb.modelvirtualbe.products.domain.model.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Override
    List<Favorite> findAll();
    Favorite findByName(String name);
    List<Favorite> findAllByIdUser(Long idUser);
}
