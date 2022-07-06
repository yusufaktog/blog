package com.folksdev.blog.dto.converter;


import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.converterhelper.DtoConverterHelper;
import com.folksdev.blog.dto.converter.summarizedconverters.SummarizedAuthorDtoConverter;
import com.folksdev.blog.dto.converter.summarizedconverters.SummarizedBlogDtoConverter;
import com.folksdev.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PostDtoConverter extends DtoConverterHelper {
    private final SummarizedAuthorDtoConverter sAuthorDtoConverter;
    private final SummarizedBlogDtoConverter sBlogDtoConverter;

    public PostDtoConverter(SummarizedAuthorDtoConverter sAuthorDtoConverter, SummarizedBlogDtoConverter sBlogDtoConverter) {
        this.sAuthorDtoConverter = sAuthorDtoConverter;
        this.sBlogDtoConverter = sBlogDtoConverter;
    }

    public PostDto convert(Post from) {
        return new PostDto(
                from.getId(),
                from.getContent(),
                from.getTime(),
                getCommentList(new ArrayList<>(from.getComments())),
                sAuthorDtoConverter.convert(from.getAuthor()),
                sBlogDtoConverter.convert(from.getBlog())
        );
    }
    public List<PostDto> convert(List<Post> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}