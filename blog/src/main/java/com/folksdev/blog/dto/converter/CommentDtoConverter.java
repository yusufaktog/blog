package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.summarizedconverters.SummarizedCommentatorDtoConverter;
import com.folksdev.blog.dto.converter.summarizedconverters.SummarizedPostDtoConverter;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {
    private final SummarizedCommentatorDtoConverter sCommentatorDtoConverter;
    private final SummarizedPostDtoConverter sPostDtoConverter;

    public CommentDtoConverter(SummarizedCommentatorDtoConverter sCommentatorDtoConverter, SummarizedPostDtoConverter sPostDtoConverter) {
        this.sCommentatorDtoConverter = sCommentatorDtoConverter;
        this.sPostDtoConverter = sPostDtoConverter;
    }

    public CommentDto convert(Comment from) {
        return new CommentDto(
                from.getComment_id(),
                from.getComment_content(),
                from.getComment_time(),
                sPostDtoConverter.convert(from.getPost()),
                sCommentatorDtoConverter.convert(from.getCommentator())

        );
    }
    public List<CommentDto> convert(List<Comment> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }


}
