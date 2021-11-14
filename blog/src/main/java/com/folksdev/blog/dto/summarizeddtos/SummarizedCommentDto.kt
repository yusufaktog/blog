package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDateTime

data class SummarizedCommentDto(
    val comment_id: String? = "",
    val comment_content: String,
    val comment_time: LocalDateTime
)