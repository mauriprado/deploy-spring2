package com.devweb.modelvirtualbe.security.domain.service.communication;

import com.devweb.modelvirtualbe.security.resource.UserResource;
import com.devweb.modelvirtualbe.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}