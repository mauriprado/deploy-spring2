package com.devweb.modelvirtualbe.security.mapping;

import com.devweb.modelvirtualbe.security.domain.model.entity.Comment;
import com.devweb.modelvirtualbe.security.resource.CommentResource;
import com.devweb.modelvirtualbe.security.resource.CreateCommentResource;
import com.devweb.modelvirtualbe.security.resource.UpdateCommentResource;
import com.devweb.modelvirtualbe.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentMapper {
    @Autowired
    EnhancedModelMapper mapper;
    public CommentResource toResource(Comment model){
        return mapper.map(model, CommentResource.class);
    }
    public List<CommentResource> ListToResource(List<Comment> modelList){
        return mapper.mapList(modelList, CommentResource.class);
    }
    public Comment toModel(CommentResource resource){
        return mapper.map(resource, Comment.class);
    }
    public Comment toModel(CreateCommentResource resource){
        return mapper.map(resource, Comment.class);
    }
    public Comment toModel (UpdateCommentResource resource){
        return mapper.map(resource, Comment.class);
    }
}
