package com.folksdev.blog.dto.summarizeddtos

import java.time.LocalDate

data class SummarizedCommentatorDto(
    val commentator_id: String? = "",
    val commentator_name: String,
    val auth_date: LocalDate

)
