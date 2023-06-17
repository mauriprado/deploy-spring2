package com.devweb.modelvirtualbe.security.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CommentResource {
    private Long id;
    private Long user_id;
    private Long product_id;
    private String description;
    private String date;
}
