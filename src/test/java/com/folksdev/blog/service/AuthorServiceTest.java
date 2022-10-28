package com.folksdev.blog.service;

import com.folksdev.blog.TestDataGenerator;
import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.converter.AuthorDtoConverter;
import com.folksdev.blog.dto.request.CreateAuthorRequest;
import com.folksdev.blog.dto.request.update.UpdateAuthorRequest;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.exception.AuthorNotFoundException;
import com.folksdev.blog.exception.BlogNotFoundException;
import com.folksdev.blog.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorServiceTest extends TestDataGenerator {
    private AuthorRepository authorRepository;
    private AuthorDtoConverter authorDtoConverter;
    private BlogService blogService;


    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        authorRepository = Mockito.mock(AuthorRepository.class);
        authorDtoConverter = Mockito.mock(AuthorDtoConverter.class);

        blogService = Mockito.mock(BlogService.class);

        authorService = new AuthorService(authorRepository, authorDtoConverter, blogService);
    }

    @Test
    void testGetAuthorById_itShouldReturnAuthorDto() {
        Author author = generateAuthor();
        AuthorDto expected = generateAuthorDto();

        Mockito.when(authorRepository.findById("author_id")).thenReturn(Optional.ofNullable(author));
        Mockito.when(authorDtoConverter.convert(author)).thenReturn(expected);

        AuthorDto actual = authorService.getAuthorById("author_id");

        assertEquals(expected, actual);

        Mockito.verify(authorRepository).findById("author_id");
        Mockito.verify(authorDtoConverter).convert(author);
    }

    @Test
    void testDeleteAuthorById_whenAuthorIdExist_itShouldReturnString() {
        Author post = generateAuthor();

        Mockito.when(authorRepository.findById("author_id")).thenReturn(Optional.of(post));

        String actual = authorService.deleteAuthorByID("author_id");

        assertEquals("delete author_id", actual);

        Mockito.verify(authorRepository).findById("author_id");

    }

    @Test
    void testDeleteByAuthorId_whenAuthorIdNotExist_itShouldThrowAuthorNotFoundException() {
        Mockito.when(authorRepository.findById("author_id")).thenThrow(AuthorNotFoundException.class);

        assertThrows(AuthorNotFoundException.class, () -> authorService.findByAuthorId("author_id"));

        Mockito.verify(authorRepository).findById("author_id");
        Mockito.verifyNoInteractions(authorDtoConverter);

    }

    @Test
    void testGetAllAuthorDtoList_itShouldReturnAuthorDtoList() {
        List<Author> authorList = generateAuthorList();
        List<AuthorDto> expected = generateAuthorDtoList();

        Mockito.when(authorRepository.findAll()).thenReturn(authorList);
        Mockito.when(authorDtoConverter.convert(authorList)).thenReturn(expected);

        List<AuthorDto> actual = authorService.getAllAuthorDtoList();

        assertEquals(expected, actual);

        Mockito.verify(authorRepository).findAll();
        Mockito.verify(authorDtoConverter).convert(authorList);
    }

    @Test
    void testGetAllAuthorList_itShouldReturnAuthorList() {
        List<Author> actual = generateAuthorList();

        Mockito.when(authorRepository.findAll()).thenReturn(actual);

        List<Author> expected = authorService.getAllAuthorList();

        assertEquals(expected, actual);

        Mockito.verify(authorRepository).findAll();
    }

    @Test
    void testCreateAuthor_whenBlogExists_itShouldReturnAuthorDto() {
        CreateAuthorRequest authorRequest = generateCreateAuthorRequest();

        Author author = generateTestAuthor();

        AuthorDto expected = generateAuthorDto();


        Mockito.when(blogService.findByBlogId("blog_id")).thenReturn(generateBlog());
        Mockito.when(authorDtoConverter.convert(author)).thenReturn(expected);
        Mockito.when(authorRepository.save(author)).thenReturn(author);

        AuthorDto actual = authorService.createAuthor("blog_id", authorRequest);

        assertEquals(expected, actual);

        Mockito.verify(blogService).findByBlogId("blog_id");
        Mockito.verify(authorDtoConverter).convert(author);
        Mockito.verify(authorRepository).save(author);

    }

    @Test
    void testCreateAuthor_whenBlogNotExists_itShouldThrowBlogNotFoundException() {
        CreateAuthorRequest authorRequest = generateCreateAuthorRequest();

        Mockito.when(blogService.findByBlogId("blog_id")).thenThrow(BlogNotFoundException.class);

        assertThrows(BlogNotFoundException.class, () -> authorService.createAuthor("blog_id", authorRequest));

        Mockito.verify(blogService).findByBlogId("blog_id");
        Mockito.verifyNoInteractions(authorDtoConverter);
        Mockito.verifyNoInteractions(authorRepository);

    }

    @Test
    void testUpdateAuthor_whenIdExist_itShouldReturnAuthorDto() {
        UpdateAuthorRequest updateAuthorRequest = generateUpdateAuthorRequest();
        Author updatedAuthor = generateUpdateAuthor(generateAuthor(), updateAuthorRequest);
        AuthorDto expected = generateAuthorDto();

        Mockito.when(authorRepository.findById("author_id")).thenReturn(Optional.ofNullable(generateAuthor()));
        Mockito.when(authorRepository.save(updatedAuthor)).thenReturn(updatedAuthor);
        Mockito.when(authorDtoConverter.convert(updatedAuthor)).thenReturn(expected);

        AuthorDto actual = authorService.updateAuthor("author_id", updateAuthorRequest);

        assertEquals(expected, actual);

        Mockito.verify(authorRepository).findById("author_id");
        Mockito.verify(authorRepository).save(updatedAuthor);
        Mockito.verify(authorDtoConverter).convert(updatedAuthor);

    }

    @Test
    void testUpdateAuthor_whenIdNotExist_itShouldThrowAuthorNotFoundException() {

        Mockito.when(authorRepository.findById("author_id")).thenThrow(AuthorNotFoundException.class);

        assertThrows(AuthorNotFoundException.class, () -> authorService.updateAuthor("author_id", generateUpdateAuthorRequest()));

        Mockito.verify(authorRepository).findById("author_id");
        Mockito.verifyNoInteractions(authorDtoConverter);

    }


}