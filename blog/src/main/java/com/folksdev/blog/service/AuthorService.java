package com.folksdev.blog.service;

import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.converter.AuthorDtoConverter;
import com.folksdev.blog.dto.request.CreateAuthorRequest;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.exception.AuthorNotFoundException;
import com.folksdev.blog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;

    public AuthorService(AuthorRepository authorRepository, AuthorDtoConverter authorDtoConverter) {
        this.authorRepository = authorRepository;
        this.authorDtoConverter = authorDtoConverter;
    }


    public AuthorDto getAuthorById(String id)  {
        return authorDtoConverter.convert(authorRepository.getById(id));

    }
    public Author findByAuthorId(String id)  {
        return authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException("Author id : " + id + " could not found"));

    }
    public String deleteAuthorByID(String authorId)  {
        authorRepository.deleteById(authorId);

        return "delete" + authorId;
    }

    public AuthorDto createAuthor(CreateAuthorRequest request) {

        Author author = new Author(
                request.getName(),
                request.getEmail(),
                request.getDateOfBirth(),
                request.getGender(),
                request.getAuth_date(),
                request.getBlog_id(),
                Collections.emptySet()
        );

        return authorDtoConverter.convert(authorRepository.save(author));
    }
    public List<AuthorDto> getAllAuthorList() {
        return authorRepository.findAll().stream().map(authorDtoConverter::convert).collect(Collectors.toList());
    }


    public AuthorDto updateAuthor(CreateAuthorRequest request)  {

        Author updatedAuthor = new Author(
                request.getName(),
                request.getEmail(),
                request.getDateOfBirth(),
                request.getGender(),
                request.getAuth_date(),
                request.getBlog_id(),
                Collections.emptySet()
        );

        return authorDtoConverter.convert(authorRepository.save(updatedAuthor));
    }
}
