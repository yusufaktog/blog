package com.folksdev.blog.dto.converter.Helper;

import com.folksdev.blog.dto.AuthorDto;
import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.PostDto;
import com.folksdev.blog.entity.Author;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverterHelper {

    protected List<AuthorDto> getAuthorList(List<Author> auhtors) {

        return auhtors.stream().map(
                a -> new AuthorDto(
                        a.getAuthor_id(),
                        a.getName(),
                        a.getEmail(),
                        a.getDateOfBirth(),
                        a.getGender(),
                        getPostList(a.getPosts().stream().collect(Collectors.toList()))
                )
        ).collect(Collectors.toList());
    }

    protected List<PostDto> getPostList(List<Post> posts) {
        return posts.stream().map(
                p -> new PostDto(
                        p.getPost_id(),
                        p.getPost_content(),
                        p.getPost_date(),
                        p.getAuthor_id(),
                        p.getBlog_id(),
                        getCommentList(p.getComments().stream().collect(Collectors.toList()))
                )
        ).collect(Collectors.toList());
    }

    protected List<CommentDto> getCommentList(List<Comment> comments) {
        return comments.stream().map(
                c -> new CommentDto(
                        c.getComment_id(),
                        c.getComment_content(),
                        c.getComment_time(),
                        c.getPost_id(),
                        c.getCommentator_id()
                )
        ).collect(Collectors.toList());
    }
}
