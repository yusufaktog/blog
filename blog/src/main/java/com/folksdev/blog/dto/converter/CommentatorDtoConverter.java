package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.converter.Helper.DtoConverterHelper;
import com.folksdev.blog.entity.Commentator;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CommentatorDtoConverter extends DtoConverterHelper {
    public CommentatorDto convert(Commentator commmentator){
        return new CommentatorDto(
                commmentator.getCommentator_id(),
                commmentator.getCommentator_name(),
                commmentator.getAuth_date(),
                commmentator.getBlog_id(),
                getCommentList(commmentator.getComments().stream().collect(Collectors.toList()))

        );
    }
}
