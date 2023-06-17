package com.devweb.modelvirtualbe.products.service;

import com.devweb.modelvirtualbe.products.domain.model.entity.Favorite;
import com.devweb.modelvirtualbe.products.domain.persistence.FavoriteRepository;
import com.devweb.modelvirtualbe.products.domain.service.FavoriteService;
import com.devweb.modelvirtualbe.shared.exception.ResourceNotFoundException;
import com.devweb.modelvirtualbe.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class FavoritesServiceImpl implements FavoriteService {

    private static final String ENTITY = "Favorite";
    private final FavoriteRepository favoriteRepository;
    private final Validator validator;

    public FavoritesServiceImpl(FavoriteRepository favoriteRepository, Validator validator) {
        this.favoriteRepository = favoriteRepository;
        this.validator = validator;
    }

    @Override
    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite getById(Long id) {
        return favoriteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public List<Favorite> getFavoriteByUserId(Long userId) {
        return favoriteRepository.findAllByIdUser(userId);
    }

    @Override
    public Favorite create(Favorite favorite) {
        Set<ConstraintViolation<Favorite>> violations = validator.validate(favorite);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite update(Long id, Favorite favorite) {
        Set<ConstraintViolation<Favorite>> violations = validator.validate(favorite);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return favoriteRepository.findById(id).map(student ->
                        favoriteRepository.save(student.withName(favorite.getName())
                                .withPrice(favorite.getPrice())
                                .withBrand(favorite.getBrand())
                                .withImage(favorite.getImage())
                                .withSizes(favorite.getSizes())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return favoriteRepository.findById(id).map(student -> {
            favoriteRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}
