package com.folksdev.blog.service;

import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.converter.BlogDtoConverter;
import com.folksdev.blog.dto.request.CreateBlogRequest;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.exception.BlogNotFoundException;
import com.folksdev.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogDtoConverter blogDtoConverter;

    public BlogService(BlogRepository blogRepository, BlogDtoConverter blogDtoConverter) {
        this.blogRepository = blogRepository;
        this.blogDtoConverter = blogDtoConverter;
    }

    public BlogDto getBlogById(String id) {
        return blogDtoConverter.convert(blogRepository.getById(id));
    }

    public Blog findByBlogId(String blogId) {
        return blogRepository.findById(blogId).orElseThrow(() -> new BlogNotFoundException("Blod with id : " + blogId + " could not found"));
    }

    public BlogDto createBlog(CreateBlogRequest request) {
        Blog blog = new Blog(
                request.getBlog_name(),
                request.getCreation_date());
        return blogDtoConverter.convert(blogRepository.save(blog));

    }

    public List<BlogDto> getAllBlogList() {
        return blogRepository.findAll().stream().map(blogDtoConverter::convert).collect(Collectors.toList());
    }

    public BlogDto updateBlog(CreateBlogRequest request) {
        Blog updatedBlog = new Blog(
                request.getBlog_id(),
                request.getBlog_name(),
                request.getCreation_date(),
                request.getAuthors(),
                request.getPosts(),
                request.getCommentators());

        return blogDtoConverter.convert(blogRepository.save(updatedBlog));
    }

    public String deleteByBlogId(String blogId) {
        blogRepository.deleteById(blogId);
        return "delete " + blogId;
    }

}