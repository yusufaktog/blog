package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentDto
import java.time.LocalDate

data class CommentatorDto(

    val id: String? = "",
    val name: String,
    val authDate: LocalDate,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<SummarizedCommentDto>? = ArrayList(),

    )