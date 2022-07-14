package com.folksdev.blog.controller;

import com.folksdev.blog.IntegrationTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CommentatorControllerTest extends IntegrationTestSupport {

    private final String URL = "/v1/commentator/";

    @Test
    public void testUpdateCommentator_whenCommentatorIdNotExist_shouldReturnCommentatorNotFoundException() throws Exception {

        this.mockMvc.perform(put(URL + "dummy_id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteCommentatorById_whenCommentatorIdNotExist_shouldReturnCommentatorNotFoundException() throws Exception {

        this.mockMvc.perform(delete(URL + "dummy_id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }
}