package com.folksdev.blog.dto.converter;

import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConverter {
    public CommentDto convert(Comment from) {
        return new CommentDto(
                from.getComment_id(),
                from.getComment_content(),
                from.getComment_time(),
                from.getPost_id(),
                from.getCommentator_id()
        );
    }


}
