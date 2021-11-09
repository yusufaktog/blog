package com.folksdev.blog.service;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.converter.CommentDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Commentator;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.exception.CommentNotFoundException;
import com.folksdev.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id: " + id + " could not found "));

    }

    public CommentDto getCommentById(String id) {
        return commentDtoConverter.convert(commentRepository.getById(id));
    }

    public List<CommentDto> getAllCommentList() {
        return commentRepository.findAll().stream().map(commentDtoConverter::convert).collect(Collectors.toList());
    }


    public CommentDto createComment(CreateCommentRequest request) {

        Commentator commentator = commentatorService.findByCommentatorId(request.getCommentator_id());
        Post post = postService.findByPostId(request.getPost_id());

        Comment comment = new Comment(request.getComment_id(),
                request.getComment_content(),
                request.getComment_time(),
                request.getPost_id(),
                post,
                commentator.getCommentator_id());

        return commentDtoConverter.convert(commentRepository.save(comment));
    }

    public String deleteByCommentId(String commentId) {
        commentRepository.deleteById(commentId);
        return "delete " + commentId;
    }

    public CommentDto updateComment(CreateCommentRequest request) {

        Comment comment = new Comment(request.getComment_id(),
                request.getComment_content(),
                request.getComment_time(),
                request.getPost_id(),
                request.getPost(),
                request.getCommentator_id());

        return commentDtoConverter.convert(commentRepository.save(comment));
    }

}
