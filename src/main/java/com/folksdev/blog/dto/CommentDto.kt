package com.folksdev.blog.dto

import com.folksdev.blog.dto.summarizeddtos.SummarizedCommentatorDto
import com.folksdev.blog.dto.summarizeddtos.SummarizedPostDto
import java.time.LocalDateTime

data class CommentDto(
    val id: String? = "",
    val content: String,
    val time: LocalDateTime,
    val post: SummarizedPostDto,
    val commentator: SummarizedCommentatorDto

)