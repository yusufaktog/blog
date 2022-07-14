package com.folksdev.blog.controller;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentRequest;
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

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable String commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }

    @PostMapping("/{postId}/{commentatorId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable String postId, @PathVariable String commentatorId,@Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(postId,commentatorId,request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
        return ResponseEntity.ok(commentService.deleteByCommentId(commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable String commentId, @Valid @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(commentId, request));
    }
}
