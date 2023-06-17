package com.devweb.modelvirtualbe.products.domain.persistence;

import com.devweb.modelvirtualbe.products.domain.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByIdAndShopId(Long Id,Long shopId);
    List<Product> findByShopId(Long shopId);

}
