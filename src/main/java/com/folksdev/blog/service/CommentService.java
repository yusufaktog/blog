package com.folksdev.blog.service;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.converter.CommentDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentRequest;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Commentator;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.exception.CommentNotFoundException;
import com.folksdev.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentatorService commentatorService;
    private final CommentDtoConverter commentDtoConverter;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository,
                          CommentatorService commentatorService,
                          CommentDtoConverter commentDtoConverter,
                          PostService postService) {
        this.commentRepository = commentRepository;
        this.commentatorService = commentatorService;
        this.commentDtoConverter = commentDtoConverter;
        this.postService = postService;
    }

    public Comment findByCommentId(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id: " + id + " could not find"));

    }

    public CommentDto getCommentById(String id) {
        return commentDtoConverter.convert(findByCommentId(id));
    }

    public List<CommentDto> getAllCommentDtoList() {
        return commentDtoConverter.convert(getAllCommentList());
    }

    public List<Comment> getAllCommentList() {
        return commentRepository.findAll();
    }


    public CommentDto createComment(String postId, String commentatorId,CreateCommentRequest request) {

        Post post = postService.findByPostId(postId);
        Commentator commentator = commentatorService.findByCommentatorId(commentatorId);

        Comment comment = new Comment(
                request.getContent(),
                request.getTime(),
                post,
                commentator);

        return commentDtoConverter.convert(commentRepository.save(comment));
    }

    public String deleteByCommentId(String commentId) {
        findByCommentId(commentId);
        commentRepository.deleteById(commentId);
        return "delete " + commentId;
    }

    public CommentDto updateComment(String id, UpdateCommentRequest request) {
        Comment comment = findByCommentId(id);

        Comment updatedComment = new Comment(
                comment.getId(),
                request.getContent(),
                comment.getTime(),
                comment.getPost(),
                comment.getCommentator());

        return commentDtoConverter.convert(commentRepository.save(updatedComment));
    }

}
