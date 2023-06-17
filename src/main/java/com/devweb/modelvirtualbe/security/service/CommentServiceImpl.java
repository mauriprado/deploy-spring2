package com.devweb.modelvirtualbe.security.service;

import com.devweb.modelvirtualbe.security.domain.model.entity.Comment;
import com.devweb.modelvirtualbe.security.domain.persistence.CommentRepository;
import com.devweb.modelvirtualbe.security.domain.service.CommentService;
import com.devweb.modelvirtualbe.shared.exception.ResourceNotFoundException;
import com.devweb.modelvirtualbe.shared.exception.ResourceValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    private static final String ENTITY = "Comment";
    private final CommentRepository commentRepository;
    private final Validator validator;

    public CommentServiceImpl(CommentRepository commentRepository, Validator validator) {
        this.commentRepository = commentRepository;
        this.validator = validator;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public Comment create(Comment comment) {
        Set<ConstraintViolation<Comment>> violations=validator.validate(comment);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update( Long id, Comment comment) {
        Set<ConstraintViolation<Comment>> violations=validator.validate(comment);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return commentRepository.findById(id).map(existingComment ->
                        commentRepository.save(existingComment.withDescription(comment.getDescription())
                                .withDate(comment.getDate())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return commentRepository.findById(id).map(comment->{
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY,id));
    }
}
