package com.folksdev.blog.controller;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
        return ResponseEntity.ok(commentService.deleteByCommentId(commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(request));
    }
}
