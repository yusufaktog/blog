package com.folksdev.blog.dto.converter.summarizedconverters;

import com.folksdev.blog.dto.summarizeddtos.SummarizedBlogDto;
import com.folksdev.blog.entity.Blog;
import org.springframework.stereotype.Component;

@Component
public class SummarizedBlogDtoConverter {
    public SummarizedBlogDto convert(Blog from){
        return  new SummarizedBlogDto(
                from.getId(),
                from.getName(),
                from.getCreationDate()
        );
    }
}
