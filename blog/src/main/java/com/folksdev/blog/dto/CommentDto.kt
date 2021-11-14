package com.folksdev.blog.dto

import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentatorDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedPostDto
import java.time.LocalDateTime

data class CommentDto(
    val comment_id: String? = "",
    val comment_content: String,
    val comment_time: LocalDateTime,
    val post: SummarizedPostDto,
    val commentator: SummarizedCommentatorDto

)