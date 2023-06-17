package com.devweb.modelvirtualbe.security.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCommentResource {
    private Long id;
    private Long product_id;
    @NotNull
    @NotBlank
    private String description;
    private String date;
}
