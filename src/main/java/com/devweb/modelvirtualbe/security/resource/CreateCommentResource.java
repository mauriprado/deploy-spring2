package com.devweb.modelvirtualbe.security.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentResource {
    private Long product_id;
    private Long user_id;
    @NotNull
    @NotBlank
    private String description;
    private String date;
}
