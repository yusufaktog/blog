package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.folksdev.blog.dto.summarizeddtos.SummarizedAuthorDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentatorDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedPostDto
import java.time.LocalDate

data class BlogDto(
    val blog_id: String? = "",
    val blog_name: String,
    val creation_date: LocalDate,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val authors: List<SummarizedAuthorDto>? = ArrayList(),

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val posts: List<SummarizedPostDto>? = ArrayList(),

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val commentators: List<SummarizedCommentatorDto>?= ArrayList()
)
