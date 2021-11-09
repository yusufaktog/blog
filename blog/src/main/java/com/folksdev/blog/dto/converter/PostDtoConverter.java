package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.dto.converter.Helper.DtoConverterHelper;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter extends DtoConverterHelper {
    public PostDto convert(Post from) {
        return new PostDto(
                from.getPost_id(),
                from.getPost_content(),
                from.getPost_date(),
                from.getAuthor_id(),
                from.getBlog_id(),
                getCommentList(from.getComments().stream().collect(Collectors.toList()))

                );
    }



}
