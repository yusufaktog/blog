package com.folksdev.blog;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.folksdev.blog.TestDataGenerator;
import com.folksdev.blog.config.DataLoader;
import com.folksdev.blog.dto.converter.*;
import com.folksdev.blog.repository.*;
import com.folksdev.blog.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
@DirtiesContext
@AutoConfigureMockMvc
public class IntegrationTestSupport extends TestDataGenerator {

    @Autowired
    public BlogService blogService;

    @Autowired
    public BlogRepository blogRepository;

    @Autowired
    public BlogDtoConverter blogDtoConverter;

    @Autowired
    public AuthorService authorService;

    @Autowired
    public AuthorRepository authorRepository;

    @Autowired
    public AuthorDtoConverter authorDtoConverter;

    @Autowired
    public PostService postService;

    @Autowired
    public PostRepository postRepository;

    @Autowired
    public PostDtoConverter postDtoConverter;

    @Autowired
    public CommentatorService commentatorService;

    @Autowired
    public CommentatorRepository commentatorRepository;

    @Autowired
    public CommentatorDtoConverter commentatorDtoConverter;

    @Autowired
    public CommentService commentService;

    @Autowired
    public CommentRepository commentRepository;

    @Autowired
    public CommentDtoConverter commentDtoConverter;

    public final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }
/*
    public List<Author> generateAuthors(int size){
        return IntStream.range(0,size)
                .mapToObj(this::generateAuthor)
                .collect(Collectors.toList());
    }

    public Author generateAuthor(int i){
        return new Author("author_name" + i ,
                "email",
                TestDataGenerator.TEST_DATE,
                Author.Gender.MALE,
                TestDataGenerator.TEST_DATE_TIME,
                Set.of(generateBlog())
                );

    }
*/
}
