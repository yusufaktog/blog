package com.folksdev.blog.service;


import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.converter.CommentatorDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentatorRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentatorRequest;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.entity.Commentator;
import com.folksdev.blog.exception.CommentatorNotFoundException;
import com.folksdev.blog.repository.CommentatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

        return commentatorRepository.findById(id).orElseThrow(() -> new CommentatorNotFoundException("Commentator with id: " + id + " could not find"));
    }

    public CommentatorDto getCommentatorById(String id){
        return commentatorDtoConverter.convert(findByCommentatorId(id));
    }

    public CommentatorDto createCommentator(String blogId, CreateCommentatorRequest request) {
        Blog blog = blogService.findByBlogId(blogId);

        Commentator Commentator = new Commentator(
                request.getCommentator_name(),
                request.getAuth_date(),
                Set.of(blog)
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
