package com.folksdev.blog.service;

import com.folksdev.blog.TestDataGenerator;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.PostDtoConverter;
import com.folksdev.blog.dto.request.CreatePostRequest;
import com.folksdev.blog.dto.request.update.UpdatePostRequest;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.exception.AuthorNotFoundException;
import com.folksdev.blog.exception.BlogNotFoundException;
import com.folksdev.blog.exception.PostNotFoundException;
import com.folksdev.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest extends TestDataGenerator {
    private PostRepository postRepository;
    private BlogService blogService;
    private AuthorService authorService;
    private PostDtoConverter postDtoConverter;
    private PostService postService;


    @BeforeEach
    void setUp() {
        postRepository = Mockito.mock(PostRepository.class);
        blogService = Mockito.mock(BlogService.class);
        authorService = Mockito.mock(AuthorService.class);
        postDtoConverter = Mockito.mock(PostDtoConverter.class);

        postService = new PostService(
                postRepository,
                blogService,
                authorService,
                postDtoConverter);
    }

    @Test
    void testCreatePost_whenBlogNotExists_shouldThrowBlogNotFoundException() {
        CreatePostRequest createPostRequest = generateCreatePostRequest();

        Mockito.when(blogService.findByBlogId(createPostRequest.getBlog().getBlog_id())).thenThrow(BlogNotFoundException.class);

        assertThrows(BlogNotFoundException.class, () -> postService.createPost(createPostRequest));

        Mockito.verify(blogService).findByBlogId(createPostRequest.getBlog().getBlog_id());
        Mockito.verifyNoInteractions(authorService);
        Mockito.verifyNoInteractions(postRepository);
        Mockito.verifyNoInteractions(postDtoConverter);

    }

    @Test
    void testCreatePost_whenBlogExistsAndAuthorNotExist_shouldThrowAuthorNotFoundException() {
        CreatePostRequest createPostRequest = generateCreatePostRequest();

        Mockito.when(blogService.findByBlogId(createPostRequest.getBlog().getBlog_id())).thenReturn(createPostRequest.getBlog());
        Mockito.when(authorService.findByAuthorId(createPostRequest.getAuthor().getAuthor_id())).thenThrow(AuthorNotFoundException.class);

        assertThrows(AuthorNotFoundException.class, () -> postService.createPost(createPostRequest));

        Mockito.verify(blogService).findByBlogId(createPostRequest.getBlog().getBlog_id());
        Mockito.verify(authorService).findByAuthorId(createPostRequest.getAuthor().getAuthor_id());
        Mockito.verifyNoInteractions(postRepository);
        Mockito.verifyNoInteractions(postDtoConverter);

    }

    @Test
    void testCreatePost_whenBlogExistsAndAuthorExist_shouldReturnPostDto() {
        CreatePostRequest createPostRequest = generateCreatePostRequest();

        Post post = generateTestPost();

        PostDto expected = generatePostDto();


        Mockito.when(blogService.findByBlogId("blog_id")).thenReturn(generateBlog());
        Mockito.when(authorService.findByAuthorId("author_id")).thenReturn(generateAuthor());
        Mockito.when(postDtoConverter.convert(post)).thenReturn(expected);
        Mockito.when(postRepository.save(post)).thenReturn(post);

        PostDto actual = postService.createPost(createPostRequest);

        assertEquals(expected, actual);

        Mockito.verify(blogService).findByBlogId("blog_id");
        Mockito.verify(authorService).findByAuthorId("author_id");
        Mockito.verify(postDtoConverter).convert(post);
        Mockito.verify(postRepository).save(post);
    }

    @Test
    void testDeleteByPostId_whenPostIdNotExist_itShouldThrowPostNotFoundException() {
        Mockito.when(postRepository.findById("post_id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class, () -> postService.findByPostId("post_id"));

        Mockito.verify(postRepository).findById("post_id");
        Mockito.verifyNoInteractions(postDtoConverter);

    }

    @Test
    void testDeletePostById_whenPostIdExist_itShouldReturnString() {
        Post post = generatePost();

        Mockito.when(postRepository.findById("post_id")).thenReturn(Optional.of(post));

        String actual = postService.deletePostByID("post_id");

        assertEquals("delete post_id", actual);

        Mockito.verify(postRepository).findById("post_id");

    }

    @Test
    void testGetAllPostDtoList_itShouldReturnPostDtoList() {
        List<Post> postList = generatePostList();
        List<PostDto> expected = generatePostDtoList();

        Mockito.when(postRepository.findAll()).thenReturn(postList);
        Mockito.when(postDtoConverter.convert(postList)).thenReturn(expected);

        List<PostDto> actual = postService.getAllPostDtoList();

        assertEquals(expected, actual);

        Mockito.verify(postRepository).findAll();
        Mockito.verify(postDtoConverter).convert(postList);
    }

    @Test
    void testGetAllPostList_itShouldReturnPostList() {
        List<Post> actual = generatePostList();
        Mockito.when(postRepository.findAll()).thenReturn(actual);

        List<Post> expected = postService.getAllPostList();

        assertEquals(expected, actual);

        Mockito.verify(postRepository).findAll();
    }

    @Test
    void testGetPostById_itShouldReturnPostDto() {
        Post post = generatePost();
        PostDto expected = generatePostDto();

        Mockito.when(postRepository.findById("post_id")).thenReturn(Optional.ofNullable(post));
        Mockito.when(postDtoConverter.convert(post)).thenReturn(expected);

        PostDto actual = postService.getPostById("post_id");

        assertEquals(expected, actual);

        Mockito.verify(postRepository).findById("post_id");
        Mockito.verify(postDtoConverter).convert(post);
    }

    @Test
    void testUpdatePost_whenIdExist_itShouldReturnPostDto() {
        UpdatePostRequest updatePostRequest = generateUpdatePostRequest();
        Post updatedPost = generateUpdatedPost(generatePost(), updatePostRequest);
        PostDto expected = generatePostDto();

        Mockito.when(postRepository.findById("post_id")).thenReturn(Optional.ofNullable(generatePost()));
        Mockito.when(postRepository.save(updatedPost)).thenReturn(updatedPost);
        Mockito.when(postDtoConverter.convert(updatedPost)).thenReturn(expected);

        PostDto actual = postService.updatePost("post_id", updatePostRequest);

        assertEquals(expected, actual);

        Mockito.verify(postRepository).findById("post_id");
        Mockito.verify(postRepository).save(updatedPost);
        Mockito.verify(postDtoConverter).convert(updatedPost);

    }
    @Test
    void testUpdatePost_whenIdNotExist_itShouldThrowPostNotFoundException() {

        Mockito.when(postRepository.findById("post_id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class,()-> postService.updatePost("post_id",generateUpdatePostRequest()));

        Mockito.verify(postRepository).findById("post_id");
        Mockito.verifyNoInteractions(postDtoConverter);

    }
}