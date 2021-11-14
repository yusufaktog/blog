package com.folksdev.blog.service;

import com.folksdev.blog.TestDataGenerator;
import com.folksdev.blog.dto.CommentatorDto;
import com.folksdev.blog.dto.converter.CommentatorDtoConverter;
import com.folksdev.blog.dto.request.CreateCommentatorRequest;
import com.folksdev.blog.dto.request.update.UpdateCommentatorRequest;
import com.folksdev.blog.entity.Commentator;
import com.folksdev.blog.exception.CommentatorNotFoundException;
import com.folksdev.blog.repository.CommentatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentatorServiceTest extends TestDataGenerator {
    private CommentatorRepository commentatorRepository;
    private CommentatorDtoConverter commentatorDtoConverter;
    
    private CommentatorService commentatorService;

    @BeforeEach
    void setUp() {
        commentatorRepository = Mockito.mock(CommentatorRepository.class);
        commentatorDtoConverter = Mockito.mock(CommentatorDtoConverter.class);
        
        commentatorService = new CommentatorService(commentatorRepository,commentatorDtoConverter);
    }

    @Test
    void testGetCommentatorById_itShouldReturnCommentatorDto() {
        Commentator commentator = generateCommentator();
        CommentatorDto expected = generateCommentatorDto();

        Mockito.when(commentatorRepository.findById("commentator_id")).thenReturn(Optional.ofNullable(commentator));
        Mockito.when(commentatorDtoConverter.convert(commentator)).thenReturn(expected);

        CommentatorDto actual = commentatorService.getCommentatorById("commentator_id");

        assertEquals(expected, actual);

        Mockito.verify(commentatorRepository).findById("commentator_id");
        Mockito.verify(commentatorDtoConverter).convert(commentator);
    }
    @Test
    void testDeleteCommentatorById_whenCommentatorIdExist_itShouldReturnString() {
        Commentator commentator = generateCommentator();

        Mockito.when(commentatorRepository.findById("commentator_id")).thenReturn(Optional.of(commentator));

        String actual = commentatorService.deleteCommentatorByID("commentator_id");

        assertEquals("delete commentator_id", actual);

        Mockito.verify(commentatorRepository).findById("commentator_id");

    }
    @Test
    void testDeleteByCommentatorId_whenCommentatorIdNotExist_itShouldThrowCommentatorNotFoundException() {
        Mockito.when(commentatorRepository.findById("commentator_id")).thenThrow(CommentatorNotFoundException.class);

        assertThrows(CommentatorNotFoundException.class, () -> commentatorService.findByCommentatorId("commentator_id"));

        Mockito.verify(commentatorRepository).findById("commentator_id");
        Mockito.verifyNoInteractions(commentatorDtoConverter);

    }
    @Test
    void testGetAllCommentatorDtoList_itShouldReturnCommentatorDtoList() {
        List<Commentator> authorList = generateCommentatorList();
        List<CommentatorDto> expected = generateCommentatorDtoList();

        Mockito.when(commentatorRepository.findAll()).thenReturn(authorList);
        Mockito.when(commentatorDtoConverter.convert(authorList)).thenReturn(expected);

        List<CommentatorDto> actual = commentatorService.getAllCommentatorDtoList();

        assertEquals(expected, actual);

        Mockito.verify(commentatorRepository).findAll();
        Mockito.verify(commentatorDtoConverter).convert(authorList);
    }

    @Test
    void testGetAllCommentatorList_itShouldReturnCommentatorList() {
        List<Commentator> actual = generateCommentatorList();

        Mockito.when(commentatorRepository.findAll()).thenReturn(actual);

        List<Commentator> expected = commentatorService.getAllCommentatorList();

        assertEquals(expected, actual);

        Mockito.verify(commentatorRepository).findAll();
    }

    @Test
    void testCreateCommentator_itShouldReturnCommentatorDto(){
        CreateCommentatorRequest authorRequest = generateCreateCommentatorRequest();

        Commentator author = generateTestCommentator();

        CommentatorDto expected = generateCommentatorDto();

        Mockito.when(commentatorRepository.save(author)).thenReturn(author);
        Mockito.when(commentatorDtoConverter.convert(author)).thenReturn(expected);

        CommentatorDto actual = commentatorService.createCommentator(authorRequest);

        assertEquals(expected,actual);

        Mockito.verify(commentatorRepository).save(author);
        Mockito.verify(commentatorDtoConverter).convert(author);

    }

    @Test
    void testUpdateCommentator_whenIdExist_itShouldReturnCommentatorDto() {
        UpdateCommentatorRequest updateCommentatorRequest = generateUpdateCommentatorRequest();
        Commentator updatedCommentator = generateUpdateCommentator(generateCommentator(), updateCommentatorRequest);
        CommentatorDto expected = generateCommentatorDto();

        Mockito.when(commentatorRepository.findById("commentator_id")).thenReturn(Optional.ofNullable(generateCommentator()));
        Mockito.when(commentatorRepository.save(updatedCommentator)).thenReturn(updatedCommentator);
        Mockito.when(commentatorDtoConverter.convert(updatedCommentator)).thenReturn(expected);

        CommentatorDto actual = commentatorService.updateCommentator("commentator_id", updateCommentatorRequest);

        assertEquals(expected, actual);

        Mockito.verify(commentatorRepository).findById("commentator_id");
        Mockito.verify(commentatorRepository).save(updatedCommentator);
        Mockito.verify(commentatorDtoConverter).convert(updatedCommentator);

    }
    @Test
    void testUpdateCommentator_whenIdNotExist_itShouldThrowCommentatorNotFoundException() {

        Mockito.when(commentatorRepository.findById("commentator_id")).thenThrow(CommentatorNotFoundException.class);

        assertThrows(CommentatorNotFoundException.class,()-> commentatorService.updateCommentator("commentator_id",generateUpdateCommentatorRequest()));

        Mockito.verify(commentatorRepository).findById("commentator_id");
        Mockito.verifyNoInteractions(commentatorDtoConverter);

    }

}