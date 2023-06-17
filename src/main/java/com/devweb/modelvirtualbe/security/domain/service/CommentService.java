package com.devweb.modelvirtualbe.security.domain.service;

import com.devweb.modelvirtualbe.security.domain.model.entity.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getById(Long id);
    Comment create(Comment comment);
    Comment update(Long id, Comment comment);
    ResponseEntity<?> delete(Long id);
}
