package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.converter.converterhelper.DtoConverterHelper;

import com.folksdev.blog.entity.Commentator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CommentatorDtoConverter extends DtoConverterHelper {
    public CommentatorDto convert(Commentator commentator){
        return new CommentatorDto(
                commentator.getId(),
                commentator.getName(),
                commentator.getAuthDate(),
                getCommentList(new ArrayList<>(commentator.getComments()))

        );
    }
    public List<CommentatorDto> convert(List<Commentator> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
