package com.devweb.modelvirtualbe.security.api;

import com.devweb.modelvirtualbe.security.domain.service.UserService;
import com.devweb.modelvirtualbe.security.domain.service.communication.AuthenticateRequest;
import com.devweb.modelvirtualbe.security.domain.service.communication.RegisterRequest;
import com.devweb.modelvirtualbe.security.mapping.UserMapper;
import com.devweb.modelvirtualbe.security.resource.UpdateUserResource;
import com.devweb.modelvirtualbe.security.resource.UserResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "acme")
@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserService userService;

    private final UserMapper mapper;

    public UsersController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }


    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }




    @GetMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserResource getUserById(@PathVariable Long userId){
        return mapper.toResource(userService.getById(userId));
    }

    @PutMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')or hasRole('USER')")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        Page<UserResource> resources = mapper.modelListToPage(userService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }


}