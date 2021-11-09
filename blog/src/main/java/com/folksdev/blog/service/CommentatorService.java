package com.folksdev.blog.service;


import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.converter.CommentatorDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentatorRequest;
import com.folksdev.blog.entity.Commentator;
import com.folksdev.blog.exception.CommentatorNotFoundException;
import com.folksdev.blog.repository.CommentatorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentatorService {

    private final CommentatorRepository commentatorRepository;
    private final CommentatorDtoConverter commentatorDtoConverter;
    private final BlogService blogService;

    public CommentatorService(CommentatorRepository commentatorRepository, CommentatorDtoConverter commentatorDtoConverter, BlogService blogService) {
        this.commentatorRepository = commentatorRepository;
        this.commentatorDtoConverter = commentatorDtoConverter;
        this.blogService = blogService;
    }

    public Commentator findByCommentatorId(String id) {

        return commentatorRepository.findById(id).orElseThrow(() -> new CommentatorNotFoundException("Commentator with id: " + id + " could not found"));
    }

    public CommentatorDto getCommentatorById(String id){
        return commentatorDtoConverter.convert(commentatorRepository.getById(id));
    }

    public CommentatorDto createCommentator(CreateCommentatorRequest request) {

        Commentator Commentator = new Commentator(
                request.getCommentator_name(),
                request.getAuth_date(),
                request.getBlog_id(),
                request.getBlogs()
        );

        return commentatorDtoConverter.convert(commentatorRepository.save(Commentator));
    }

    public List<CommentatorDto> getAllCommentatorList() {
        return commentatorRepository.findAll().stream().map(commentatorDtoConverter::convert).collect(Collectors.toList());
    }

    public String deleteCommentatorByID(String CommentatorId) {
        commentatorRepository.deleteById(CommentatorId);

        return "delete" + CommentatorId;
    }

    public CommentatorDto updateCommentator(CreateCommentatorRequest request) {

        Commentator updatedCommentator = new Commentator(
                request.getCommentator_id(),
                request.getCommentator_name(),
                request.getAuth_date(),
                request.getBlog_id(),
                Collections.emptySet(),
                Collections.emptySet()
        );

        return commentatorDtoConverter.convert(commentatorRepository.save(updatedCommentator));
    }
}
