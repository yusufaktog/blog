package com.folksdev.blog.service;

import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.PostDtoConverter;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.exception.PostNotFoundException;
import com.folksdev.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final BlogService blogService;
    private final AuthorService authorService;
    private final PostDtoConverter postDtoConverter;

    public PostService(PostRepository postRepository, BlogService blogService, AuthorService authorService, PostDtoConverter postDtoConverter) {
        this.postRepository = postRepository;
        this.blogService = blogService;
        this.authorService = authorService;
        this.postDtoConverter = postDtoConverter;
    }

    public Post findByPostId(String id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post with id: " + id + " could not found"));

    }
    public PostDto getPostById(String id) {
        return postDtoConverter.convert(postRepository.getById(id));

    }

    public List<PostDto> getAllPostList() {
        return postRepository.findAll().stream().map(postDtoConverter::convert).collect(Collectors.toList());
    }

    public PostDto createPost(CreatePostRequest request) {
        Blog blog = blogService.findByBlogId(request.getBlog_id());
        Author author = authorService.findByAuthorId(request.getAuthor_id());

        Post Post = new Post(
                request.getPost_content(),
                request.getPost_date(),
                request.getBlog_id(),
                request.getBlog_id(),
                author,
                blog
        );

        return postDtoConverter.convert(postRepository.save(Post));
    }

    public String deletePostByID(String PostId) {
        postRepository.deleteById(PostId);

        return "delete" + PostId;
    }

    public PostDto updatePost( CreatePostRequest request) {

        Post updatedPost = new Post(
                request.getPost_id(),
                request.getPost_content(),
                request.getPost_date(),
                request.getBlog_id(),
                request.getBlog_id(),
                request.getAuthor(),
                Collections.emptySet(),
                request.getBlog()
        );

        return postDtoConverter.convert(postRepository.save(updatedPost));
    }
}
