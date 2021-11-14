package com.folksdev.blog.dto.converter.converterhelper;

import com.folksdev.blog.dto.summarizeddtos.*;
import com.folksdev.blog.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConverterHelper {


    protected List<SummarizedAuthorDto> getAuthorList(List<Author> authors) {

        return authors.stream().map(
                a -> new SummarizedAuthorDto(
                        a.getAuthor_id(),
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
                        c.getComment_id(),
                        c.getComment_content(),
                        c.getComment_time()

                )
        ).collect(Collectors.toList());
    }

    protected List<SummarizedCommentatorDto> getCommentatorList(List<Commentator> commentators) {
        return commentators.stream().map(
                c -> new SummarizedCommentatorDto(
                        c.getCommentator_id(),
                        c.getCommentator_name(),
                        c.getAuth_date()

                )
        ).collect(Collectors.toList());
    }

    protected List<SummarizedBlogDto> getBlogList(List<Blog> blogs) {
        return blogs.stream().map(
                b -> new SummarizedBlogDto(
                        b.getBlog_id(),
                        b.getBlog_name(),
                        b.getCreation_date()
                )
        ).collect(Collectors.toList());

    }

    protected List<SummarizedPostDto> getPostList(List<Post> posts) {
        return posts.stream().map(
                p -> new SummarizedPostDto(
                        p.getPost_id(),
                        p.getPost_content(),
                        p.getPost_date())).collect(Collectors.toList());

    }

}