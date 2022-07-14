package com.folksdev.blog.service;

import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.converter.BlogDtoConverter;
import com.folksdev.blog.dto.request.CreateBlogRequest;
import com.folksdev.blog.dto.request.update.UpdateBlogRequest;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.exception.BlogNotFoundException;
import com.folksdev.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogDtoConverter blogDtoConverter;

    public BlogService(BlogRepository blogRepository, BlogDtoConverter blogDtoConverter) {
        this.blogRepository = blogRepository;
        this.blogDtoConverter = blogDtoConverter;
    }

    public BlogDto getBlogById(String id) {
        return blogDtoConverter.convert(findByBlogId(id));
    }

    public Blog findByBlogId(String blogId) {
        return blogRepository.findById(blogId).orElseThrow(() -> new BlogNotFoundException("Blog with id : " + blogId + " could not find"));
    }

    public BlogDto createBlog(CreateBlogRequest request) {
        Blog blog = new Blog(
                request.getName(),
                request.getCreationDate());
        return blogDtoConverter.convert(blogRepository.save(blog));

    }

    public List<BlogDto> getAllBlogDtoList() {
        return blogDtoConverter.convert(getAllBlogList());
    }

    public List<Blog> getAllBlogList() {
        return blogRepository.findAll();
    }

    public BlogDto updateBlog(String blogId, UpdateBlogRequest request) {
        Blog blog = findByBlogId(blogId);
        Blog updatedBlog = new Blog(
                blog.getId(),
                request.getName(),
                blog.getCreationDate(),
                blog.getAuthors(),
                blog.getPosts(),
                blog.getCommentators()
        );

        return blogDtoConverter.convert(blogRepository.save(updatedBlog));
    }

    public String deleteByBlogId(String blogId) {
        findByBlogId(blogId);
        blogRepository.deleteById(blogId);
        return "delete " + blogId;
    }

}