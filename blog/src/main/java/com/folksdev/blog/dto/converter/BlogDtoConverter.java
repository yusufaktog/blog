package com.folksdev.blog.dto.converter;


import com.folksdev.blog.dto.BlogDto;
import com.folksdev.blog.dto.converter.converterhelper.DtoConverterHelper;
import com.folksdev.blog.entity.Blog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BlogDtoConverter extends DtoConverterHelper {

    public BlogDto convert(Blog from){
        return new BlogDto(
                from.getBlog_id(),
                from.getBlog_name(),
                from.getCreation_date(),
                getAuthorList(new ArrayList<>(from.getAuthors())),
                getPostList(new ArrayList<>(from.getPosts())),
                getCommentatorList(new ArrayList<>(from.getCommentators()))


        );
    }
    public List<BlogDto> convert(List<Blog> blog) {
        return blog.stream().map(this::convert).collect(Collectors.toList());
    }

}