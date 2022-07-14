package com.folksdev.blog.controller;

import com.folksdev.blog.IntegrationTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class CommentControllerTest extends IntegrationTestSupport {

    private final String URL = "/v1/comment/";

    @Test
    public void testUpdateAuthor_whenAuthorIdNotExist_shouldReturnAuthorNotFoundException() throws Exception {

        this.mockMvc.perform(put(URL + "dummy_id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testDeleteAuthorById_whenAuthorIdNotExist_shouldReturnAuthorNotFoundException() throws Exception {

        this.mockMvc.perform(delete(URL + "dummy_id")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    
}
