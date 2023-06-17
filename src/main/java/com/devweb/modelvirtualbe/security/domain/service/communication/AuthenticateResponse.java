package com.devweb.modelvirtualbe.security.domain.service.communication;

import com.devweb.modelvirtualbe.security.resource.AuthenticateResource;
import com.devweb.modelvirtualbe.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}