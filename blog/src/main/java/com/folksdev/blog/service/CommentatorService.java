package com.folksdev.blog.service;


import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.converter.CommentatorDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentatorRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentatorRequest;
import com.folksdev.blog.entity.Comment;
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

    public CommentatorService(CommentatorRepository commentatorRepository, CommentatorDtoConverter commentatorDtoConverter) {
        this.commentatorRepository = commentatorRepository;
        this.commentatorDtoConverter = commentatorDtoConverter;
    }

    public Commentator findByCommentatorId(String id) {

        return commentatorRepository.findById(id).orElseThrow(() -> new CommentatorNotFoundException("Commentator with id: " + id + " could not find"));
    }

    public CommentatorDto getCommentatorById(String id){
        return commentatorDtoConverter.convert(findByCommentatorId(id));
    }

    public CommentatorDto createCommentator(CreateCommentatorRequest request) {

        Commentator Commentator = new Commentator(
                request.getCommentator_name(),
                request.getAuth_date(),
                request.getBlogs()
        );

        return commentatorDtoConverter.convert(commentatorRepository.save(Commentator));
    }

    public List<CommentatorDto> getAllCommentatorDtoList() {
        return commentatorDtoConverter.convert(getAllCommentatorList());
    }
    public List<Commentator> getAllCommentatorList() {
        return commentatorRepository.findAll();
    }

    public String deleteCommentatorByID(String commentatorId) {
        findByCommentatorId(commentatorId);

        commentatorRepository.deleteById(commentatorId);

        return "delete " + commentatorId;
    }

    public CommentatorDto updateCommentator(String id, UpdateCommentatorRequest request) {
        Commentator commentator = findByCommentatorId(id);

        Commentator updatedCommentator = new Commentator(
                commentator.getCommentator_id(),
                request.getCommentator_name(),
                commentator.getAuth_date(),
                commentator.getComments(),
                commentator.getBlogs()
        );

        return commentatorDtoConverter.convert(commentatorRepository.save(updatedCommentator));
    }
}
