package com.folksdev.blog.service;

import com.folksdev.blog.TestDataGenerator;
import com.folksdev.blog.dto.CommentDto;
import com.folksdev.blog.dto.converter.CommentDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentRequest;
import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.exception.CommentNotFoundException;
import com.folksdev.blog.exception.CommentatorNotFoundException;
import com.folksdev.blog.exception.PostNotFoundException;
import com.folksdev.blog.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentServiceTest extends TestDataGenerator {
    private CommentRepository commentRepository;
    private PostService postService;
    private CommentatorService commentatorService;
    private CommentDtoConverter commentDtoConverter;

    private CommentService commentService;


    @BeforeEach
    void setUp() {
        commentRepository = Mockito.mock(CommentRepository.class);
        postService = Mockito.mock(PostService.class);
        commentatorService = Mockito.mock(CommentatorService.class);
        commentDtoConverter = Mockito.mock(CommentDtoConverter.class);

        commentService = new CommentService(
                commentRepository,
                commentatorService,
                commentDtoConverter,
                postService);
    }

    @Test
    void testCreateComment_whenPostNotExists_shouldThrowPostNotFoundException() {
        CreateCommentRequest createCommentRequest = generateCreateCommentRequest();

        Mockito.when(postService.findByPostId("post_id")).thenThrow(PostNotFoundException.class);

        assertThrows(PostNotFoundException.class, () -> commentService.createComment("post_id","commentator_id",createCommentRequest));

        Mockito.verify(postService).findByPostId("post_id");
        Mockito.verifyNoInteractions(commentatorService);
        Mockito.verifyNoInteractions(commentRepository);
        Mockito.verifyNoInteractions(commentDtoConverter);

    }

    @Test
    void testCreateComment_whenPostExistsAndCommentatorNotExist_shouldThrowCommentatorNotFoundException() {
        CreateCommentRequest createCommentRequest = generateCreateCommentRequest();

        Mockito.when(postService.findByPostId("post_id")).thenReturn(generatePost());
        Mockito.when(commentatorService.findByCommentatorId("commentator_id")).thenThrow(CommentatorNotFoundException.class);

        assertThrows(CommentatorNotFoundException.class, () -> commentService.createComment("post_id","commentator_id",createCommentRequest));

        Mockito.verify(postService).findByPostId("post_id");
        Mockito.verify(commentatorService).findByCommentatorId("commentator_id");
        Mockito.verifyNoInteractions(commentRepository);
        Mockito.verifyNoInteractions(commentDtoConverter);

    }

    @Test
    void testCreateComment_whenPostExistsAndCommentatorExist_shouldReturnCommentDto() {
        CreateCommentRequest createCommentRequest = generateCreateCommentRequest();

        Comment comment = generateTestComment();

        CommentDto expected = generateCommentDto();


        Mockito.when(commentatorService.findByCommentatorId("commentator_id")).thenReturn(generateCommentator());
        Mockito.when(postService.findByPostId("post_id")).thenReturn(generatePost());
        Mockito.when(commentDtoConverter.convert(comment)).thenReturn(expected);
        Mockito.when(commentRepository.save(comment)).thenReturn(comment);

        CommentDto actual = commentService.createComment("post_id","commentator_id",createCommentRequest);

        assertEquals(expected, actual);

        Mockito.verify(postService).findByPostId("post_id");
        Mockito.verify(commentatorService).findByCommentatorId("commentator_id");
        Mockito.verify(commentDtoConverter).convert(comment);
        Mockito.verify(commentRepository).save(comment);
    }

    @Test
    void testDeleteByCommentId_whenCommentIdNotExist_itShouldThrowCommentNotFoundException() {
        Mockito.when(commentRepository.findById("comment_id")).thenThrow(CommentNotFoundException.class);

        assertThrows(CommentNotFoundException.class, () -> commentService.findByCommentId("comment_id"));

        Mockito.verify(commentRepository).findById("comment_id");
        Mockito.verifyNoInteractions(commentDtoConverter);

    }

    @Test
    void testDeleteCommentById_whenCommentIdExist_itShouldReturnString() {
        Comment comment = generateComment();

        Mockito.when(commentRepository.findById("comment_id")).thenReturn(Optional.of(comment));

        String actual = commentService.deleteByCommentId("comment_id");

        assertEquals("delete comment_id", actual);

        Mockito.verify(commentRepository).findById("comment_id");

    }

    @Test
    void testGetAllCommentDtoList_itShouldReturnCommentDtoList() {
        List<Comment> postList = generateCommentList();
        List<CommentDto> expected = generateCommentDtoList();

        Mockito.when(commentRepository.findAll()).thenReturn(postList);
        Mockito.when(commentDtoConverter.convert(postList)).thenReturn(expected);

        List<CommentDto> actual = commentService.getAllCommentDtoList();

        assertEquals(expected, actual);

        Mockito.verify(commentRepository).findAll();
        Mockito.verify(commentDtoConverter).convert(postList);
    }

    @Test
    void testGetAllCommentList_itShouldReturnCommentList() {
        List<Comment> actual = generateCommentList();
        Mockito.when(commentRepository.findAll()).thenReturn(actual);

        List<Comment> expected = commentService.getAllCommentList();

        assertEquals(expected, actual);

        Mockito.verify(commentRepository).findAll();
    }

    @Test
    void testGetCommentById_itShouldReturnCommentDto() {
        Comment comment = generateComment();
        CommentDto expected = generateCommentDto();

        Mockito.when(commentRepository.findById("comment_id")).thenReturn(Optional.ofNullable(comment));
        Mockito.when(commentDtoConverter.convert(comment)).thenReturn(expected);

        CommentDto actual = commentService.getCommentById("comment_id");

        assertEquals(expected, actual);

        Mockito.verify(commentRepository).findById("comment_id");
        Mockito.verify(commentDtoConverter).convert(comment);
    }

    @Test
    void testUpdateComment_whenIdExist_itShouldReturnCommentDto() {
        UpdateCommentRequest updateCommentRequest = generateUpdateCommentRequest();
        Comment updatedComment = generateUpdatedComment(generateComment(), updateCommentRequest);
        CommentDto expected = generateCommentDto();

        Mockito.when(commentRepository.findById("comment_id")).thenReturn(Optional.ofNullable(generateComment()));
        Mockito.when(commentRepository.save(updatedComment)).thenReturn(updatedComment);
        Mockito.when(commentDtoConverter.convert(updatedComment)).thenReturn(expected);

        CommentDto actual = commentService.updateComment("comment_id", updateCommentRequest);

        assertEquals(expected, actual);

        Mockito.verify(commentRepository).findById("comment_id");
        Mockito.verify(commentRepository).save(updatedComment);
        Mockito.verify(commentDtoConverter).convert(updatedComment);

    }

    @Test
    void testUpdateComment_whenIdNotExist_itShouldThrowCommentNotFoundException() {

        Mockito.when(commentRepository.findById("comment_id")).thenThrow(CommentNotFoundException.class);

        assertThrows(CommentNotFoundException.class, () -> commentService.updateComment("comment_id", generateUpdateCommentRequest()));

        Mockito.verify(commentRepository).findById("comment_id");
        Mockito.verifyNoInteractions(commentDtoConverter);

    }
}