package com.devweb.modelvirtualbe.security.domain.persistence;

import com.devweb.modelvirtualbe.security.domain.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long Id);
}
