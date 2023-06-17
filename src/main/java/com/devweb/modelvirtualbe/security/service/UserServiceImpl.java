package com.devweb.modelvirtualbe.security.service;

import com.devweb.modelvirtualbe.security.domain.model.entity.Role;
import com.devweb.modelvirtualbe.security.domain.model.entity.User;
import com.devweb.modelvirtualbe.security.domain.model.enumeration.Roles;
import com.devweb.modelvirtualbe.security.domain.persistence.RoleRepository;
import com.devweb.modelvirtualbe.security.domain.persistence.UserRepository;
import com.devweb.modelvirtualbe.security.domain.service.UserService;
import com.devweb.modelvirtualbe.security.domain.service.communication.AuthenticateRequest;
import com.devweb.modelvirtualbe.security.domain.service.communication.AuthenticateResponse;
import com.devweb.modelvirtualbe.security.domain.service.communication.RegisterRequest;
import com.devweb.modelvirtualbe.security.domain.service.communication.RegisterResponse;
import com.devweb.modelvirtualbe.security.middleware.JwtHandler;
import com.devweb.modelvirtualbe.security.middleware.UserDetailsImpl;
import com.devweb.modelvirtualbe.security.resource.AuthenticateResource;
import com.devweb.modelvirtualbe.security.resource.UserResource;
import com.devweb.modelvirtualbe.shared.exception.ResourceNotFoundException;
import com.devweb.modelvirtualbe.shared.exception.ResourceValidationException;
import com.devweb.modelvirtualbe.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String ENTITY = "User";
    private final Validator validator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandler handler;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EnhancedModelMapper mapper;

    public UserServiceImpl(Validator validator) {
        this.validator = validator;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);

            AuthenticateResponse response = new AuthenticateResponse(resource);

            return ResponseEntity.ok(response.getResource());


        } catch (Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            AuthenticateResponse response = new AuthenticateResponse("Username is already used.");
            return ResponseEntity.badRequest()
                    .body(response.getMessage());
        }


        try {

            Set<String> rolesStringSet = request.getRoles();
            Set<Role> roles = new HashSet<>();

            if (rolesStringSet == null) {
                roleRepository.findByName(Roles.ROLE_USER)
                        .map(roles::add)
                        .orElseThrow(() -> new RuntimeException("Role not found."));
            } else {
                rolesStringSet.forEach(roleString ->
                        roleRepository.findByName(Roles.valueOf(roleString))
                                .map(roles::add)
                                .orElseThrow(() -> new RuntimeException("Role not found.")));
            }

            logger.info("Roles: {}", roles);

            User user = new User()
                    .withUsername(request.getUsername())
                    .withPassword(encoder.encode(request.getPassword()))
                    .withFirstName(request.getFirstName())
                    .withLastName(request.getLastName())
                    .withYear(request.getYear())
                    .withProfileImage(request.getProfileImage())
                    .withRoles(roles);


            userRepository.save(user);
            UserResource resource = mapper.map(user, UserResource.class);
            RegisterResponse response = new RegisterResponse(resource);
            return ResponseEntity.ok(response.getResource());

        } catch (Exception e) {

            RegisterResponse response = new RegisterResponse(e.getMessage());
            return ResponseEntity.badRequest().body(response.getMessage());

        }


    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User update(Long userId, User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Optional<User> userWithUsername = userRepository.findByUsername(user.getUsername());

        if(userWithUsername.isPresent() && !userWithUsername.get().getId().equals(userId))
            throw new ResourceValidationException(ENTITY, "An user with the same email already exists.");

        return userRepository.findById(userId).map(existingUser ->
                        userRepository.save(
                                existingUser
                                        .withUsername(user.getUsername())
                                        .withLastName(user.getLastName())
                                        .withFirstName(user.getFirstName())
                                        .withYear(user.getYear())
                                        .withProfileImage(user.getProfileImage())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

}