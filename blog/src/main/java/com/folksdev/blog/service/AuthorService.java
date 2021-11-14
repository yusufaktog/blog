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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;

    public AuthorService(AuthorRepository authorRepository, AuthorDtoConverter authorDtoConverter) {
        this.authorRepository = authorRepository;
        this.authorDtoConverter = authorDtoConverter;
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

    public AuthorDto createAuthor(CreateAuthorRequest request) {

        Author author = new Author(
                request.getAuthor_name(),
                request.getEmail(),
                request.getDateOfBirth(),
                request.getGender(),
                request.getAuth_date(),
                Collections.emptySet()
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
                author.getAuthor_id(),
                request.getName(),
                request.getEmail(),
                author.getDateOfBirth(),
                request.getGender(),
                author.getAuth_date(),
                author.getPosts(),
                author.getBlogs()
        );

        return authorDtoConverter.convert(authorRepository.save(updatedAuthor));
    }
}
