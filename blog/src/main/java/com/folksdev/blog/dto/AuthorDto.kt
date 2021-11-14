package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.folksdev.blog.dto.summarizeddtos.SummarizedBlogDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedPostDto
import com.folksdev.blog.entity.Author
import java.time.LocalDate

data class AuthorDto(
    val author_id: String? = "",
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate?,
    val gender: Author.Gender,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<SummarizedPostDto>? = ArrayList(),
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val blogs: List<SummarizedBlogDto>? = ArrayList()


)
