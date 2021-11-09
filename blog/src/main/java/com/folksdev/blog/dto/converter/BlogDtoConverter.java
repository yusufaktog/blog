package com.folksdev.blog.dto.converter;


import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.converter.Helper.DtoConverterHelper;
import com.folksdev.blog.entity.Blog;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BlogDtoConverter extends DtoConverterHelper {

    public BlogDto convert(Blog from){
        return new BlogDto(
                from.getBlog_id(),
                from.getBlog_name(),
                from.getCreation_date(),
                getAuthorList(from.getAuthors().stream().collect(Collectors.toList()))
        );
    }


}
