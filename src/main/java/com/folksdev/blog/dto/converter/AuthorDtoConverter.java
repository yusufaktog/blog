package com.folksdev.blog.dto.converter;


import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.converter.converterhelper.DtoConverterHelper;
import com.folksdev.blog.entity.Author;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class AuthorDtoConverter extends DtoConverterHelper {

    public AuthorDto convert(Author from) {
        return new AuthorDto(
                from.getId(),
                from.getName(),
                from.getEmail(),
                from.getDateOfBirth(),
                from.getGender(),
                getPostList(new ArrayList<>(from.getPosts())),
                getBlogList(new ArrayList<>(from.getBlogs()))

        );

    }
    public List<AuthorDto> convert(List<Author> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}