package com.folksdev.blog.service;

import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.PostDtoConverter;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.dto.request.update.UpdatePostRequest;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.exception.PostNotFoundException;
import com.folksdev.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final BlogService blogService;
    private final AuthorService authorService;
    private final PostDtoConverter postDtoConverter;

    public PostService(PostRepository postRepository,
                       BlogService blogService,
                       AuthorService authorService,
                       PostDtoConverter postDtoConverter) {
        this.postRepository = postRepository;
        this.blogService = blogService;
        this.authorService = authorService;
        this.postDtoConverter = postDtoConverter;
    }

    public Post findByPostId(String id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post with id: " + id + " could not find"));

    }
    public PostDto getPostById(String id) {
        return postDtoConverter.convert(findByPostId(id));
    }

    public List<Post> getAllPostList() {
        return postRepository.findAll();
    }
    public List<PostDto> getAllPostDtoList() {
        return postDtoConverter.convert(getAllPostList());
    }

    public PostDto createPost(CreatePostRequest request) {

        Blog blog = blogService.findByBlogId(request.getBlog().getBlog_id());
        Author author = authorService.findByAuthorId(request.getAuthor().getAuthor_id());

        Post Post = new Post(
                request.getPost_content(),
                request.getPost_date(),
                author,
                blog
        );

        return postDtoConverter.convert(postRepository.save(Post));
    }

    public String deletePostByID(String postId) {
        findByPostId(postId);
        postRepository.deleteById(postId);

        return "delete " + postId;
    }

    public PostDto updatePost(String id, UpdatePostRequest request) {
        Post post = findByPostId(id);

        Post updatedPost = new Post(
                post.getPost_id(),
                request.getPost_content(),
                post.getPost_date(),
                post.getAuthor(),
                post.getComments(),
                post.getBlog()
        );

        return postDtoConverter.convert(postRepository.save(updatedPost));
    }
}
