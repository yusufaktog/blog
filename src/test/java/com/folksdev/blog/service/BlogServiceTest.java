package com.folksdev.blog.service;

import com.folksdev.blog.TestDataGenerator;
import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.BlogDtoConverter;
import com.folksdev.blog.dto.request.CreateBlogRequest;
import com.folksdev.blog.dto.request.update.UpdateBlogRequest;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.exception.BlogNotFoundException;
import com.folksdev.blog.exception.PostNotFoundException;
import com.folksdev.blog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BlogServiceTest extends TestDataGenerator {
    private BlogRepository blogRepository;
    private BlogDtoConverter blogDtoConverter;
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        blogRepository = Mockito.mock(BlogRepository.class);
        blogDtoConverter = Mockito.mock(BlogDtoConverter.class);
        blogService = new BlogService(blogRepository, blogDtoConverter);


    }

    @Test
    void test_getBlogById_itShouldReturnBlogDto() {
        BlogDto expected = generateBlogDto();
        Blog blog = generateBlog();

        Mockito.when(blogRepository.findById("blog_id")).thenReturn(java.util.Optional.ofNullable(blog));
        Mockito.when(blogDtoConverter.convert(blog)).thenReturn(expected);

        BlogDto actual = blogService.getBlogById("blog_id");
        assertEquals(expected, actual);

        Mockito.verify(blogRepository).findById("blog_id");
        Mockito.verify(blogDtoConverter).convert(blog);
    }


    @Test
    void testGetAllBlogDtoList_itShouldReturnBlogDtoList() {
        List<Blog> blogList = generateBLogList();
        List<BlogDto> expected = generateBlogDtoList();

        Mockito.when(blogRepository.findAll()).thenReturn(blogList);
        Mockito.when(blogDtoConverter.convert(blogList)).thenReturn(expected);

        List<BlogDto> actual = blogService.getAllBlogDtoList();

        assertEquals(expected, actual);

        Mockito.verify(blogRepository).findAll();
        Mockito.verify(blogDtoConverter).convert(blogList);
    }

    @Test
    void testGetAllBlogList_itShouldReturnBlogList() {
        List<Blog> actual = generateBLogList();
        Mockito.when(blogRepository.findAll()).thenReturn(actual);

        List<Blog> expected = blogService.getAllBlogList();

        assertEquals(expected, actual);

        Mockito.verify(blogRepository).findAll();
    }

    @Test
    void testGetBLogById_itShouldReturnBLogDto() {
        Blog blog = generateBlog();
        BlogDto expected = generateBlogDto();

        Mockito.when(blogRepository.findById("blog_id")).thenReturn(Optional.ofNullable(blog));
        Mockito.when(blogDtoConverter.convert(blog)).thenReturn(expected);

        BlogDto actual = blogService.getBlogById("blog_id");

        assertEquals(expected, actual);

        Mockito.verify(blogRepository).findById("blog_id");
        Mockito.verify(blogDtoConverter).convert(blog);
    }

    @Test
    void testCreateBLog_itShouldReturnBlogDto(){
        CreateBlogRequest createBlogRequest = generateCreateBLogRequest();

        Blog blog = generateTestBlog();
        BlogDto expected = generateBlogDto();

        Mockito.when(blogDtoConverter.convert(blog)).thenReturn(expected);
        Mockito.when(blogRepository.save(blog)).thenReturn(blog);

        BlogDto actual = blogService.createBlog(createBlogRequest);

        assertEquals(expected,actual);

        Mockito.verify(blogDtoConverter).convert(blog);
        Mockito.verify(blogRepository).save(blog);
    }
    @Test
    void testDeleteByBlogId_whenBlogIdNotExist_itShouldThrowBlogNotFoundException() {
        Mockito.when(blogRepository.findById("blog_id")).thenThrow(BlogNotFoundException.class);

        assertThrows(BlogNotFoundException.class, () -> blogService.findByBlogId("blog_id"));

        Mockito.verify(blogRepository).findById("blog_id");
        Mockito.verifyNoInteractions(blogDtoConverter);

    }

    @Test
    void testDeleteBlogById_whenBlogIdExist_itShouldReturnString() {
        Blog post = generateBlog();

        Mockito.when(blogRepository.findById("blog_id")).thenReturn(Optional.of(post));

        String actual = blogService.deleteByBlogId("blog_id");

        assertEquals("delete blog_id", actual);

        Mockito.verify(blogRepository).findById("blog_id");

    }
    
    @Test
    void testUpdateBlog_whenBlogIdExist_itShouldReturnString(){
        UpdateBlogRequest updateBlogRequest = generateUpdateBlogRequest();
        Blog updatedBLog = generateUpdatedBlog(generateBlog(), updateBlogRequest);
        BlogDto expected = generateBlogDto();

        Mockito.when(blogRepository.findById("blog_id")).thenReturn(Optional.ofNullable(generateBlog()));
        Mockito.when(blogRepository.save(updatedBLog)).thenReturn(updatedBLog);
        Mockito.when(blogDtoConverter.convert(updatedBLog)).thenReturn(expected);

        BlogDto actual = blogService.updateBlog("blog_id", updateBlogRequest);

        assertEquals(expected, actual);

        Mockito.verify(blogRepository).findById("blog_id");
        Mockito.verify(blogRepository).save(updatedBLog);
        Mockito.verify(blogDtoConverter).convert(updatedBLog);
        
    }

    @Test
    void testUpdateBlog_whenIdNotExist_itShouldThrowBlogNotFoundException() {

        Mockito.when(blogRepository.findById("blog_id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class,()-> blogService.updateBlog("blog_id",generateUpdateBlogRequest()));

        Mockito.verify(blogRepository).findById("blog_id");
        Mockito.verifyNoInteractions(blogDtoConverter);

    }

}