package com.devweb.modelvirtualbe.security.domain.service;

import com.devweb.modelvirtualbe.security.domain.model.entity.User;
import com.devweb.modelvirtualbe.security.domain.service.communication.AuthenticateRequest;
import com.devweb.modelvirtualbe.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseEntity<?> authenticate(AuthenticateRequest request);

    ResponseEntity<?> register(RegisterRequest request);

    User update(Long id, User user);
    List<User> getAll();
    User getById(Long userId);

}