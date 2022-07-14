package com.folksdev.blog.config;


import com.folksdev.blog.repository.AuthorRepository;
import com.folksdev.blog.repository.BlogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;



@Component
@ConditionalOnProperty(name = "command.line.runner.enable", havingValue = "true")
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
