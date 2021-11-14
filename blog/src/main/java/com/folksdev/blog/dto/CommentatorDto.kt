package com.folksdev.blog.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentDto
import java.time.LocalDate

data class CommentatorDto(

    val commentator_id: String? = "",
    val commentator_name: String,
    val auth_date: LocalDate,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val comments: List<SummarizedCommentDto>? = ArrayList(),

    )