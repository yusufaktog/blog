package com.folksdev.blog.service;

import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.converter.AuthorDtoConverter;
import com.folksdev.blog.dto.request.CreateAuthorRequest;
import com.folksdev.blog.dto.request.update.UpdateAuthorRequest;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.exception.AuthorNotFoundException;
import com.folksdev.blog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;
    private final BlogService blogService;

    public AuthorService(AuthorRepository authorRepository, AuthorDtoConverter authorDtoConverter, BlogService blogService) {
        this.authorRepository = authorRepository;
        this.authorDtoConverter = authorDtoConverter;
        this.blogService = blogService;
    }

    public AuthorDto getAuthorById(String id) {
        return authorDtoConverter.convert(findByAuthorId(id));

    }

    public Author findByAuthorId(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author id : " + id + " could not found"));

    }

    public String deleteAuthorByID(String authorId) {
        findByAuthorId(authorId);

        return "delete " + authorId;
    }

    public AuthorDto createAuthor(String blogId, CreateAuthorRequest request) {
        Blog blog = blogService.findByBlogId(blogId);

        Author author = new Author(
                request.getName(),
                request.getEmail(),
                request.getDateOfBirth(),
                request.getGender(),
                request.getAuthDate(),
                Set.of(blog)
        );

        return authorDtoConverter.convert(authorRepository.save(author));
    }

    public List<AuthorDto> getAllAuthorDtoList() {
        return authorDtoConverter.convert(getAllAuthorList());
    }

    public List<Author> getAllAuthorList() {
        return authorRepository.findAll();
    }

    public AuthorDto updateAuthor(String id, UpdateAuthorRequest request) {
        Author author = findByAuthorId(id);

        Author updatedAuthor = new Author(
                author.getId(),
                request.getName(),
                request.getEmail(),
                author.getDateOfBirth(),
                request.getGender(),
                author.getAuthDate(),
                author.getPosts(),
                author.getBlogs()
        );

        return authorDtoConverter.convert(authorRepository.save(updatedAuthor));
    }
}
