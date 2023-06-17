package com.devweb.modelvirtualbe.products.domain.persistence;

import com.devweb.modelvirtualbe.products.domain.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Override
    List<Shop> findAll();

    Optional<Shop> findById(Long id);
    Shop findByLogoUrl(String logoUrl);
    Shop findByName(String name);
    Shop findByNameAndIdNot(String name, Long id);
    Shop findByLogoUrlAndIdNot(String logoUrl, Long id);
}
