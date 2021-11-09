package com.folksdev.blog;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Override
    public void run(String... args) {
       /* Blog blog = new Blog("test blog",LocalDate.now());
        Author author = new Author("Josef","josef@example.com",LocalDate.now(), Author.Gender.FEMALE, LocalDateTime.now(), blog.getBlog_id(), Collections.emptySet());
        Post post = new Post("test post", LocalDate.now(),author.getAuthor_id(), blog.getBlog_id(), author, blog);
        Commentator commentator = new Commentator("Test Commentator",LocalDate.now(),blog.getBlog_id(),Set.of(blog));
        Comment comment = new Comment("Test comment",LocalDateTime.now(),post.getPost_id(),post,commentator);*/

    }
}
