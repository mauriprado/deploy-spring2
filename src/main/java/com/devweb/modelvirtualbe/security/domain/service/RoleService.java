package com.devweb.modelvirtualbe.security.domain.service;

import com.devweb.modelvirtualbe.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();

    List<Role> getAll();
}