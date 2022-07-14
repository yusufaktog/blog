package com.folksdev.blog.dto.converter.converterhelper;

import com.folksdev.blog.dto.summarizeddtos.*;
import com.folksdev.blog.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverterHelper {


    protected List<SummarizedAuthorDto> getAuthorList(List<Author> authors) {

        return authors.stream().map(
                a -> new SummarizedAuthorDto(
                        a.getId(),
                        a.getName(),
                        a.getEmail(),
                        a.getDateOfBirth(),
                        a.getGender()

                )
        ).collect(Collectors.toList());
    }


    protected List<SummarizedCommentDto> getCommentList(List<Comment> comments) {
        return comments.stream().map(
                c -> new SummarizedCommentDto(
                        c.getId(),
                        c.getContent(),
                        c.getTime()

                )
        ).collect(Collectors.toList());
    }

    protected List<SummarizedCommentatorDto> getCommentatorList(List<Commentator> commentators) {
        return commentators.stream().map(
                c -> new SummarizedCommentatorDto(
                        c.getId(),
                        c.getName(),
                        c.getAuthDate()

                )
        ).collect(Collectors.toList());
    }

    protected List<SummarizedBlogDto> getBlogList(List<Blog> blogs) {
        return blogs.stream().map(
                b -> new SummarizedBlogDto(
                        b.getId(),
                        b.getName(),
                        b.getCreationDate()
                )
        ).collect(Collectors.toList());

    }

    protected List<SummarizedPostDto> getPostList(List<Post> posts) {
        return posts.stream().map(
                p -> new SummarizedPostDto(
                        p.getId(),
                        p.getContent(),
                        p.getTime())).collect(Collectors.toList());

    }

}