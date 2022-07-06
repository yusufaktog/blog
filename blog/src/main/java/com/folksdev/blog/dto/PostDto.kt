package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.folksdev.blog.dto.summarizeddtos.SummarizedAuthorDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedBlogDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentDto
import java.time.LocalDate

data class PostDto(
    val id: String? = "",
    val content: String,
    val date: LocalDate,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<SummarizedCommentDto>? = ArrayList(),
    val author: SummarizedAuthorDto,
    val blog: SummarizedBlogDto

)