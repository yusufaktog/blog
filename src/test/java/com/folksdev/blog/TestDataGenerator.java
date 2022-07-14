package com.folksdev.blog;

import com.folksdev.blog.dto.*;
import com.folksdev.blog.dto.request.*;
import com.folksdev.blog.dto.request.update.*;
import com.folksdev.blog.dto.summarizeddtos.*;
import com.folksdev.blog.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TestDataGenerator {

    public static final LocalDate TEST_DATE = LocalDate.of(2011, 11, 11);
    public static final LocalTime TEST_TIME = LocalTime.of(11, 11, 11);
    public static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(TEST_DATE, TEST_TIME);


    public Blog generateBlog() {
        return new Blog(
                "blog_id",
                "blog_name",
                TEST_DATE,
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptySet()
        );
    }

    public Blog generateTestBlog() {
        return new Blog(
                "blog_name",
                TEST_DATE
        );

    }

    public Author generateAuthor() {
        return new Author(
                "author_id",
                "author_name",
                "email",
                TEST_DATE,
                Author.Gender.MALE,
                TEST_DATE_TIME,
                Collections.emptySet(),
                Set.of(generateBlog())
        );
    }

    public Author generateTestAuthor() {
        return new Author(
                "author_name",
                "email",
                TEST_DATE,
                Author.Gender.MALE,
                TEST_DATE_TIME,
                Set.of(generateBlog())
        );
    }

    public Post generatePost() {
        return new Post(
                "post_id",
                "post_content",
                TEST_DATE,
                generateAuthor(),
                generateBlog()
        );
    }

    public Post generateTestPost() {
        return new Post(
                "post_content",
                TEST_DATE,
                generateAuthor(),
                generateBlog()
        );
    }

    public Commentator generateCommentator() {
        return new Commentator(
                "commentator_id",
                "commentator_name",
                TEST_DATE,
                Collections.emptySet(),
                Set.of(generateBlog())
        );
    }

    public Commentator generateTestCommentator() {

        return new Commentator(
                "commentator_name",
                TEST_DATE,
                Set.of(generateBlog())
        );
    }


    public Comment generateComment() {
        return new Comment(
                "comment_id",
                "comment_content",
                TEST_DATE_TIME,
                generatePost(),
                generateCommentator()
        );
    }

    public Comment generateTestComment() {
        return new Comment(
                "comment_content",
                TEST_DATE_TIME,
                generatePost(),
                generateCommentator()
        );
    }

    public BlogDto generateBlogDto() {
        return new BlogDto(
                "blog_id",
                "blog_name",
                TEST_DATE,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public AuthorDto generateAuthorDto() {
        return new AuthorDto(
                "author_id",
                "author_name",
                "email",
                TEST_DATE,
                Author.Gender.MALE,
                Collections.emptyList(),
                List.of(generateSummarizedBlogDto())
        );
    }

    public PostDto generatePostDto() {
        return new PostDto(
                "post_id",
                "post_content",
                TEST_DATE,
                Collections.emptyList(),
                createSummarizedAuthorDto(),
                createSummarizedBlogDto()
        );
    }

    public CommentatorDto generateCommentatorDto() {
        return new CommentatorDto(
                "commentator_id",
                "commentator_name",
                TEST_DATE,
                Collections.emptyList()
        );
    }

    public CommentDto generateCommentDto() {
        return new CommentDto(
                "comment_id",
                "comment_content",
                TEST_DATE_TIME,
                createSummarizedPostDto(),
                createSummarizedCommentatorDto()


        );
    }

    public SummarizedBlogDto createSummarizedBlogDto() {
        return new SummarizedBlogDto(
                "blog_id",
                "blog_name",
                TEST_DATE
        );

    }

    public SummarizedAuthorDto createSummarizedAuthorDto() {
        return new SummarizedAuthorDto(
                "author_id",
                "author_name",
                "email",
                TEST_DATE,
                Author.Gender.MALE
        );
    }

    public SummarizedPostDto createSummarizedPostDto() {
        return new SummarizedPostDto(
                "post_id",
                "post_content",
                TEST_DATE
        );
    }

    public SummarizedCommentatorDto createSummarizedCommentatorDto() {
        return new SummarizedCommentatorDto(
                "commentator_id",
                "commentator_name",
                TEST_DATE
        );
    }

    public SummarizedBlogDto generateSummarizedBlogDto() {
        return new SummarizedBlogDto(
                "blog_id",
                "blog_name",
                TEST_DATE
        );
    }

    public SummarizedCommentDto createSummarizedCommentDto() {
        return new SummarizedCommentDto(
                "comment_id",
                "comment_content",
                TEST_DATE_TIME
        );
    }

    public CreateBlogRequest generateCreateBLogRequest() {
        return new CreateBlogRequest(
                "blog_name",
                TEST_DATE
        );
    }

    public CreateAuthorRequest generateCreateAuthorRequest() {
        return new CreateAuthorRequest(
                "author_name",
                "email",
                TEST_DATE,
                Author.Gender.MALE,
                TEST_DATE_TIME
        );
    }

    public CreatePostRequest generateCreatePostRequest() {
        return new CreatePostRequest(
                "post_content",
                TEST_DATE
        );
    }

    public CreateCommentatorRequest generateCreateCommentatorRequest() {
        return new CreateCommentatorRequest(
                "commentator_name",
                TEST_DATE
        );
    }

    public CreateCommentRequest generateCreateCommentRequest() {
        return new CreateCommentRequest(
                "comment_content",
                TEST_DATE_TIME
        );
    }

    public List<Blog> generateBLogList() {
        return List.of(generateBlog());
    }

    public List<BlogDto> generateBlogDtoList() {
        return List.of(generateBlogDto());
    }

    public List<Post> generatePostList() {
        return List.of(generatePost());
    }

    public List<Author> generateAuthorList() {
        return List.of(generateAuthor());
    }

    public List<Commentator> generateCommentatorList() {
        return List.of(generateCommentator());
    }

    public List<CommentatorDto> generateCommentatorDtoList() {
        return List.of(generateCommentatorDto());
    }

    public List<AuthorDto> generateAuthorDtoList() {
        return List.of(generateAuthorDto());
    }

    public List<Comment> generateCommentList() {
        return List.of(generateComment());
    }

    public List<CommentDto> generateCommentDtoList() {
        return List.of(generateCommentDto());
    }

    public List<PostDto> generatePostDtoList() {
        return List.of(generatePostDto());
    }

    public UpdatePostRequest generateUpdatePostRequest() {
        return new UpdatePostRequest("post_content");
    }

    public UpdateCommentRequest generateUpdateCommentRequest() {
        return new UpdateCommentRequest("comment_content");
    }

    public UpdateBlogRequest generateUpdateBlogRequest() {
        return new UpdateBlogRequest("blog_name");
    }

    public UpdateAuthorRequest generateUpdateAuthorRequest() {
        return new UpdateAuthorRequest("author_name", "email", Author.Gender.MALE);
    }

    public UpdateCommentatorRequest generateUpdateCommentatorRequest() {
        return new UpdateCommentatorRequest("commentator_name");
    }

    public Commentator generateUpdateCommentator(Commentator commentator, UpdateCommentatorRequest updateCommentatorRequest) {
        return new Commentator(
                commentator.getId(),
                updateCommentatorRequest.getName(),
                commentator.getAuthDate(),
                commentator.getComments(),
                commentator.getBlogs()
        );
    }

    public Author generateUpdateAuthor(Author author, UpdateAuthorRequest updateAuthorRequest) {
        return new Author(
                author.getId(),
                updateAuthorRequest.getName(),
                updateAuthorRequest.getEmail(),
                author.getDateOfBirth(),
                updateAuthorRequest.getGender(),
                author.getAuthDate(),
                author.getPosts(),
                author.getBlogs()
        );

    }

    public Comment generateUpdatedComment(Comment comment, UpdateCommentRequest updateCommentRequest) {
        return new Comment(
                comment.getId(),
                updateCommentRequest.getContent(),
                TEST_DATE_TIME,
                comment.getPost(),
                comment.getCommentator()
        );

    }

    public Post generateUpdatedPost(Post post, UpdatePostRequest updatePostRequest) {
        return new Post(
                post.getId(),
                updatePostRequest.getContent(),
                post.getTime(),
                post.getAuthor(),
                post.getComments(),
                post.getBlog());
    }

    public Blog generateUpdatedBlog(Blog blog, UpdateBlogRequest updateBlogRequest) {
        return new Blog(
                blog.getId(),
                updateBlogRequest.getName(),
                blog.getCreationDate(),
                blog.getAuthors(),
                blog.getPosts(),
                blog.getCommentators()
        );
    }


}