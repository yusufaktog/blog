package com.folksdev.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentatorNotFoundException extends RuntimeException{

    public CommentatorNotFoundException(String message) {
        super(message);
    }
}