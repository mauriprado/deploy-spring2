package com.devweb.modelvirtualbe.products.api;

import com.devweb.modelvirtualbe.products.domain.model.entity.Favorite;
import com.devweb.modelvirtualbe.products.domain.service.FavoriteService;
import com.devweb.modelvirtualbe.products.mapping.FavoriteMapper;
import com.devweb.modelvirtualbe.products.resource.CreateFavoriteResource;
import com.devweb.modelvirtualbe.products.resource.FavoriteResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name = "acme")
@Tag(name = "Favorites")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/favorites")
public class FavoritesController {

    private final FavoriteService favoriteService;
    private final FavoriteMapper mapper;

    public FavoritesController(FavoriteService favoriteService, FavoriteMapper mapper) {
        this.favoriteService = favoriteService;
        this.mapper = mapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<Favorite> getAllFavorites(){
        return favoriteService.getAll();
    }

    @GetMapping("{favoritesId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public FavoriteResource getFavoritesId(@PathVariable Long favoritesId){
        return mapper.toResource(favoriteService.getById(favoritesId));
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public List<Favorite> getFavoriteByUserId(@PathVariable Long userId){
        return favoriteService.getFavoriteByUserId(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public FavoriteResource createFavorite(@RequestBody CreateFavoriteResource resource){
        return mapper.toResource(favoriteService.create(mapper.toModel(resource)));
    }

    @DeleteMapping("{favoriteId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> deleteShop(@PathVariable Long favoriteId){
        return favoriteService.delete(favoriteId);
    }
}
