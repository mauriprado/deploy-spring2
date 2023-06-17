package com.devweb.modelvirtualbe.products.service;

import com.devweb.modelvirtualbe.products.domain.model.entity.Product;
import com.devweb.modelvirtualbe.products.domain.persistence.ProductRepository;
import com.devweb.modelvirtualbe.products.domain.persistence.ShopRepository;
import com.devweb.modelvirtualbe.products.domain.service.ProductService;
import com.devweb.modelvirtualbe.shared.exception.ResourceNotFoundException;
import com.devweb.modelvirtualbe.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String ENTITY="Product";
    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;
    private final Validator validator;

    public ProductServiceImpl(ProductRepository productRepository,ShopRepository shopRepository,Validator validator){
        this.productRepository=productRepository;
        this.shopRepository=shopRepository;
        this.validator=validator;
    }

    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public List<Product> getAllByShopId(Long shopId){
        return productRepository.findByShopId(shopId);
    }

    @Override
    public Product create(Long shopId,Product request){
        Set<ConstraintViolation<Product>> violations=validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY,violations);

        return shopRepository.findById(shopId).map(shop->{
            request.setShop(shop);
            return productRepository.save(request);
        }).orElseThrow(()->new ResourceNotFoundException("Shop",shopId));
    }

    @Override
    public Product update(Long shopId,Long productId,Product request){
        Set<ConstraintViolation<Product>> violations=validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(!shopRepository.existsById(shopId))
            throw new ResourceNotFoundException("Shop",shopId);
        return productRepository.findById(productId).map(existingProduct->
                productRepository.save(existingProduct
                        .withName(request.getName())
                        .withImage(request.getImage())
                        .withPrice(request.getPrice())))
                .orElseThrow(() -> new ResourceNotFoundException("Shop", shopId));
    }

    @Override
    public ResponseEntity<?> delete(Long shopId,Long productId){
        return productRepository.findByIdAndShopId(productId,shopId).map(product->{
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY,productId));
    }
}
