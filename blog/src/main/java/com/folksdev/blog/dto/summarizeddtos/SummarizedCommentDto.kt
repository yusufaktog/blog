package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDateTime

data class SummarizedCommentDto(
    val id: String? = "",
    val content: String,
    val time: LocalDateTime
)