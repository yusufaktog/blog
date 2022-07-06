package com.folksdev.blog.dto.converter.summarizedconverters;

import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentatorDto;
import com.folksdev.blog.entity.Commentator;
import org.springframework.stereotype.Component;

@Component
public class SummarizedCommentatorDtoConverter {
    public SummarizedCommentatorDto convert(Commentator from){
        return new SummarizedCommentatorDto(
                from.getId(),
                from.getName(),
                from.getAuthDate()
        );
    }
}
