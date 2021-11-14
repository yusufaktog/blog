package com.folksdev.blog.config;

import com.folksdev.blog.entity.Author;
import com.folksdev.blog.entity.Blog;
import com.folksdev.blog.repository.AuthorRepository;
import com.folksdev.blog.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {



    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;

    public DataLoader(BlogRepository blogRepository, AuthorRepository authorRepository) {
        this.blogRepository = blogRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) {

    }


}
