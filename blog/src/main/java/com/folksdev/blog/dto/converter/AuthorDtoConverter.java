package com.folksdev.blog.dto.converter;


import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.converter.Helper.DtoConverterHelper;
import com.folksdev.blog.entity.Author;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorDtoConverter extends DtoConverterHelper {

    public AuthorDto convert(Author from) {
        return new AuthorDto(
                from.getAuthor_id(),
                from.getName(),
                from.getEmail(),
                from.getDateOfBirth(),
                from.getGender(),
                getPostList(from.getPosts().stream().collect(Collectors.toList()))

        );

    }

}
