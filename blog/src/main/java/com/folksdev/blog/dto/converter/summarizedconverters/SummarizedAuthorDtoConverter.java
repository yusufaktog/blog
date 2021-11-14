package com.folksdev.blog.dto.converter.summarizedconverters;

import com.folksdev.blog.dto.summarizeddtos.SummarizedAuthorDto;
import com.folksdev.blog.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class SummarizedAuthorDtoConverter {

    public SummarizedAuthorDto convert(Author from) {
        return new SummarizedAuthorDto(
                from.getAuthor_id(),
                from.getName(),
                from.getEmail(),
                from.getDateOfBirth(),
                from.getGender()
        );
    }
}
