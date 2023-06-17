package com.devweb.modelvirtualbe.products.service;

import com.devweb.modelvirtualbe.products.domain.model.entity.Shop;
import com.devweb.modelvirtualbe.products.domain.persistence.ShopRepository;
import com.devweb.modelvirtualbe.products.domain.service.ShopService;
import com.devweb.modelvirtualbe.shared.exception.ResourceNotFoundException;
import com.devweb.modelvirtualbe.shared.exception.ResourceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShopServiceImpl implements ShopService {

    private static final String ENTITY = "Shop";
    private final ShopRepository shopRepository;
    private final Validator validator;

    public ShopServiceImpl(ShopRepository shopRepository, Validator validator) {
        this.shopRepository = shopRepository;
        this.validator = validator;
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getById(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Shop create(Shop shop) {
        Set<ConstraintViolation<Shop>> violations = validator.validate(shop);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Shop shopWithLogoUrl = shopRepository.findByLogoUrl(shop.getLogoUrl());
        if (shopWithLogoUrl != null)
            throw new ResourceValidationException(ENTITY, "A shop with the same logo already exists. Change logo's url and try again.");
        Shop shopWithName = shopRepository.findByName(shop.getName());
        if (shopWithName != null)
            throw new ResourceValidationException(ENTITY, "A shop with the same name already exists. Change shop name and try again.");
        return shopRepository.save(shop);
    }

    @Override
    public Shop update(Long id, Shop shop) {
        Set<ConstraintViolation<Shop>> violations = validator.validate(shop);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        Shop shopWithLogoUrl = shopRepository.findByLogoUrlAndIdNot(shop.getLogoUrl(), id);
        if (shopWithLogoUrl != null)
            throw new ResourceValidationException(ENTITY, "A shop with the same logo already exists. Change logo's url and try again.");
        Shop shopWithName = shopRepository.findByNameAndIdNot(shop.getName(), id);
        if (shopWithName != null)
            throw new ResourceValidationException(ENTITY, "A shop with the same name already exists. Change shop name and try again.");
        return shopRepository.findById(id).map(s ->
                shopRepository.save(
                        s
                                .withName(shop.getName())
                                .withLogoUrl(shop.getLogoUrl())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return shopRepository.findById(id).map(shop -> {
            shopRepository.delete(shop);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, id));
    }
}
