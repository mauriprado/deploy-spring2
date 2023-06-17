package com.devweb.modelvirtualbe.security.api;

import com.devweb.modelvirtualbe.security.domain.service.CommentService;
import com.devweb.modelvirtualbe.security.mapping.CommentMapper;
import com.devweb.modelvirtualbe.security.resource.CommentResource;
import com.devweb.modelvirtualbe.security.resource.CreateCommentResource;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name="acme")
@Tag(name="Comment")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CommentResource> getAllComment(){
        return mapper.ListToResource(commentService.getAll());
    }

    @GetMapping("{commentId}")
    public CommentResource getCommentById(@PathVariable Long commentId){
        return mapper.toResource(commentService.getById(commentId));
    }
    @PostMapping("")
    public CommentResource createComment(@RequestBody CreateCommentResource resource){
        return mapper.toResource(commentService.create(mapper.toModel(resource)));
    }
}
