package com.folksdev.blog;

import com.folksdev.blog.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Blog blog = new Blog("test blog",LocalDate.now());
        Author author = new Author("Josef", LocalDate.now(), Gender.FEMALE, LocalDateTime.now(), blog.getBlog_id(), Collections.emptySet());
        Post post = new Post("test post", LocalDate.now(), author.getAuthor_id(), blog.getBlog_id(), author, blog);
        Commentator commentator = new Commentator("Test Commentator",LocalDate.now(),blog.getBlog_id(),Set.of(blog));
        Comment comment = new Comment("Test comment",LocalDateTime.now(),post.getPost_id(),post,commentator);
		/*
		*     val author_id : String?,
    val name: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val auth_date: LocalDateTime,
    val blog_id: String,

    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    val posts: Set<Post>? = HashSet(),

    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    val blogs: Set<Blog>*/


    }
}
